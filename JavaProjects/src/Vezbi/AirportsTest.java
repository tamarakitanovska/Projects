package Vezbi;

import java.util.*;
import java.util.stream.Collectors;

class Airport{
    String name;
    String country;
    String code;
    int passengers;

    public Airport(String name, String country, String code, int passengers) {
        this.name = name;
        this.country = country;
        this.code = code;
        this.passengers = passengers;
    }
    public String toString(){
        return name + "\n";
    }
}
class SetOfFlights{
    TreeSet<Flight> setFlights;
    public SetOfFlights(){
        setFlights = new TreeSet<>(Comparator.comparing(Flight::getFrom).thenComparing(Comparator.comparing(Flight::getTime)));
    }
    public void addFlight(String from,String to, int time, int duration){
        setFlights.add(new Flight(from,to,time,duration));
    }
    public void pecati(){
        setFlights.stream()
                .forEach(System.out::println);
    }
}
class Time{
    int hours;
    int minutes;
    public Time(int hours, int minutes){
        this.hours=hours;
        this.minutes=minutes;
    }
    public String toString(){
        return hours+":"+minutes;
    }
}
class TimeConverter{
    public static Time ConcertToHours(int number){
        int hours = number/60;
        int minutes = number%60;
        return new Time(hours,minutes);
    }
}
class Flight{
    String from;
    String to;
    int time;
    int duration;

    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }
    public String toString(){
        int i=0;
        return ++i +". " + " " + from + " " + TimeConverter.ConcertToHours(time) + duration + "\n";
    }
}
class Airports{
    HashMap<String,Airport> hashAirports;
    HashMap<String,SetOfFlights> hashFlights;
    public Airports(){
        hashAirports = new HashMap<>();
        hashFlights = new HashMap<>();
    }
    public void addAirport(String name, String country, String code, int passengers){
        hashAirports.put(code,new Airport(name,country,code,passengers));
    }
    public void addFlights(String from, String to, int time, int duration){
        SetOfFlights sof = hashFlights.computeIfAbsent(from,k->new SetOfFlights());
        sof.addFlight(from,to,time,duration);
    }
    public void showFlightsFromAirport(String code){
        Airport a = hashAirports.get(code);
        System.out.println(a);
        SetOfFlights s = hashFlights.get(code);
        s.pecati();
    }
    public void showDirectFlightsFromTo(String from, String to){
        SetOfFlights s = hashFlights.get(from);
        List<Flight> eq = s.setFlights.stream()
                .filter(x->{
                    return x.getTo().equals(to);
                })
                .collect(Collectors.toList());
        eq.stream()
                .forEach(System.out::println);


    }
    public void showDirectFlightsTo(String to){
        List<Flight> list = hashFlights.values().stream()
                .flatMap(x->x.setFlights.stream())
                .filter(x->{
                    return x.getTo().equals(to);
                })
                .collect(Collectors.toList());
        list.stream()
                .forEach(System.out::println);
    }
}
public class AirportsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airports airports = new Airports();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] codes = new String[n];
        for (int i = 0; i < n; ++i) {
            String al = scanner.nextLine();
            String[] parts = al.split(";");
            airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            codes[i] = parts[2];
        }
        int nn = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nn; ++i) {
            String fl = scanner.nextLine();
            String[] parts = fl.split(";");
            airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        int f = scanner.nextInt();
        int t = scanner.nextInt();
        String from = codes[f];
        String to = codes[t];
        System.out.printf("===== FLIGHTS FROM %S =====\n", from);
        airports.showFlightsFromAirport(from);
        System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
        airports.showDirectFlightsFromTo(from, to);
        t += 5;
        t = t % n;
        to = codes[t];
        System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
        airports.showDirectFlightsTo(to);
    }
}

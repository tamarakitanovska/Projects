package Vezbi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

class Measurment implements Comparable<Measurment>{
    float temperature;
    float wind;
    float humidity;
    float visibility;
    Date date;

    public Measurment(float temperature, float wind, float humidity, float visibility, Date date) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }
    public boolean checkDays(Date m2,int days){
        double dateDays1;
        double dateDays2;
        dateDays1 = date.getTime()/(1000*60*60*24);
        dateDays2 = m2.getTime()/(1000*60*60*24);
            if(dateDays2-dateDays1>days){
                return true;
        }
        return false;
    }

    public Date getDate() {
        return date;
    }

    public float getTemperature() {
        return temperature;
    }
    @Override
    public String toString() {
        return temperature + " " + wind + " " + humidity + " " + visibility + " " + date.toString();
    }

    @Override
    public int compareTo(Measurment o) {
        return getDate().compareTo(o.getDate());
    }
}

class WeatherStation{
    int days;
    TreeSet<Measurment> list;
    public WeatherStation(int days){
        this.days=days;
        list = new TreeSet<>();
    }
    public boolean checkMinutes(Date date){
        long date1ToMinutes = date.getTime()/(1000*60);
        for (Measurment m: list) {
            long date2ToMinutes = m.date.getTime()/(1000*60);
                if(date1ToMinutes-date2ToMinutes<2.5){
                    return false;
                }
            }
        return true;
    }
    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date){
        Measurment measurment = new Measurment(temperature,wind,humidity,visibility,date);
        if(checkMinutes(date)){
            list.add(measurment);
        }
        List<Measurment> list2 = list.stream()
                .filter(m->m.checkDays(date,days))
                .collect(Collectors.toList());
        list2.stream()
                .forEach(m->list.remove(m));
        }
    public int total(){
        return list.size();
    }
    public void status(Date from, Date to){
        List<Measurment> list1 = list.stream()
                .filter(d->d.getDate().compareTo(from)>=0 && d.getDate().compareTo(to)<=0)
                .sorted(Comparator.comparing(Measurment::getDate))
                .collect(Collectors.toList());
        if(list1.size()==0){
            throw new RuntimeException();
        }
        list1.stream()
                .forEach(System.out::println);
        System.out.println(String.format("%.2f",list1.stream()
                .mapToDouble(Measurment::getTemperature)
                .average()
                .getAsDouble()));
    }
}
public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            Date date = df.parse(line);
            ws.addMeasurment(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        Date from = df.parse(line);
        line = scanner.nextLine();
        Date to = df.parse(line);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}

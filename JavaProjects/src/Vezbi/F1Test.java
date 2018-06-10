package Vezbi;

import javax.print.DocFlavor;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class F1Race {
    ArrayList<Vozac> list;
    public F1Race(){
        list = new ArrayList();
    }
    public void readResults(InputStream inputStream) {
        String line;
        String[] niza;
        try {
            Scanner sc = new Scanner(inputStream);
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                niza = line.split(" ");
                Vozac vozac = new Vozac(niza[0], niza[1], niza[2], niza[3]);
                list.add(vozac);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
    void printSorted(OutputStream outputStream){
        Collections.sort(list);
        Collections.reverse(list);
        StringBuilder sb = new StringBuilder();
        PrintStream output = new PrintStream(outputStream);
        int i=0;
        for(Vozac a : list){
            sb.append(String.format("%d. %-10s%10s\n",++i, a.getName(), a.getBestTime()));
        }
        output.println(sb.toString());
    }
}
class Vozac implements Comparable{
    String name;
    String time1;
    String time2;
    String time3;
    String bestTime;

    public Vozac(String name, String time1, String time2, String time3) {
        this.name = name;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        bestTime = najdobroVreme(time1,time2,time3);
    }
    public String najdobroVreme(String t1,String t2, String t3){
        String podobro;
        String najdobro;
        podobro = podobroVreme(t1,t2);
        najdobro = podobroVreme(podobro,t3);
        return najdobro;
    }
    public String podobroVreme(String t1, String t2){
        String[] time1 = t1.split(":");
        String[] time2 = t2.split(":");
        if(Integer.parseInt(time1[0])>Integer.parseInt(time2[0])){
            return t2;
        }
        else if(Integer.parseInt(time1[0])<Integer.parseInt(time2[0])){
            return t1;
        }
        else if(Integer.parseInt(time1[1])>Integer.parseInt(time2[1])){
            return t2;
        }
        else if(Integer.parseInt(time1[1])<Integer.parseInt(time2[1])){
            return t1;
        }
        else if(Integer.parseInt(time1[2])>Integer.parseInt(time2[2])){
            return t2;
        }
        else if(Integer.parseInt(time1[2])<Integer.parseInt(time2[2])){
            return t1;
        }
        else return t1;
    }

    @Override
    public int compareTo(Object o) {
        Vozac v = (Vozac) o;
        String result = podobroVreme(bestTime,v.bestTime);
        if(result.equals(bestTime)){
            return 1;
        }
        else if(result.equals(v.bestTime)){
            return -1;
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public String getTime1() {
        return time1;
    }

    public String getTime2() {
        return time2;
    }

    public String getTime3() {
        return time3;
    }

    public String getBestTime() {
        return bestTime;
    }
}
public class F1Test {
        public static String file = "file12.txt";
        public static void main(String[] args) throws IOException {
            F1Race f1Race = new F1Race();
            f1Race.readResults(System.in);
            f1Race.printSorted(System.out);
    }
}

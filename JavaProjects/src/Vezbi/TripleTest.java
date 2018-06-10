package Vezbi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Triple<T extends Number>{
    ArrayList<T> list;
    private T number1;
    private T number2;
    private T number3;

    public Triple(T number1, T number2, T number3) {
        list = new ArrayList<>();
        list.add(number1);
        list.add(number2);
        list.add(number3);
    }
    public double max(){
       return Math.max(Math.max(list.get(0).doubleValue(),list.get(1).doubleValue()),list.get(2).doubleValue());
    }
    public double avarage(){
        double suma =0;
        suma = list.get(0).doubleValue()+list.get(1).doubleValue()+list.get(2).doubleValue();
        return suma/3;
    }
    public void sort(){
        Collections.sort(list,Comparator.comparing(Number::doubleValue));
    }
    public String toString(){
        return String.format("%.2f %.2f %.2f",list.get(0).doubleValue(), list.get(1).doubleValue(), list.get(2).doubleValue());
    }

}
public class TripleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
        System.out.printf("%.2f\n", tInt.max());
        System.out.printf("%.2f\n", tInt.avarage());
        tInt.sort();
        System.out.println(tInt);
        float fa = scanner.nextFloat();
        float fb = scanner.nextFloat();
        float fc = scanner.nextFloat();
        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
        System.out.printf("%.2f\n", tFloat.max());
        System.out.printf("%.2f\n", tFloat.avarage());
        tFloat.sort();
        System.out.println(tFloat);
        double da = scanner.nextDouble();
        double db = scanner.nextDouble();
        double dc = scanner.nextDouble();
        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
        System.out.printf("%.2f\n", tDouble.max());
        System.out.printf("%.2f\n", tDouble.avarage());
        tDouble.sort();
        System.out.println(tDouble);
    }
}

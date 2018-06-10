package Vezbi;

import java.util.ArrayList;
import java.util.Scanner;

class MinMax<T extends Comparable>{
    T min;
    T max;
    int brojac;
    public MinMax(){
        brojac = 0;
    }
    public void update(T element){
        if(min==null && max==null){
            min=max=element;
        }
        if(element.compareTo(min)!=0 && element.compareTo(max)!=0){
            brojac++;
        }
        if(element.compareTo(min)<=0){
            min=element;
        }
        if(element.compareTo(max)>=0){
            max = element;
        }
    }
    public T max(){
        return max;
    }
    public T min(){
        return min;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(min + " ");
        sb.append(max + " ");
        sb.append(brojac + " ");
        return sb.toString();
    }
}
public class MinAndMax {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for(int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for(int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}

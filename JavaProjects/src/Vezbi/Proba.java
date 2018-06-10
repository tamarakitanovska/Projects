package Vezbi;

import java.io.IOException;

class Kocka{
    String name;
    int broj;
    public Kocka(String name){
        this.name=name;
    }
}
public class Proba {
    public static void main(String[] args){
        Kocka kocka = new Kocka("Kockaaa");
        kocka.broj++;
        System.out.println(kocka.broj);
    }
}

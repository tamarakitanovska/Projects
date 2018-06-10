package Vezbi;

import java.util.*;

class IntegerList{
    private ArrayList<Integer> lista;
    public IntegerList(){
        lista = new ArrayList<>();
    }
    public IntegerList(int size){
        lista = new ArrayList<>();
    }
    public IntegerList(Integer[] numbers){
        lista = new ArrayList<>(Arrays.asList(numbers));
    }
    public void add(int el, int idx){
        if(idx>lista.size()){
            for(int i=lista.size();i<idx;i++){
                lista.add(i,0);
            }
        }
        lista.add(idx,el);
    }
    public int remove(int idx){
        Integer a = lista.get(idx);
        lista.remove(idx);
        return a;
    }
    public void set(int el, int idx){
        lista.set(idx,el);
    }
    public int get(int idx){
        return lista.get(idx);
    }
    public int size(){
        return lista.size();
    }
    public int count(int el){
        int broj=0;
        for(Integer a : lista){
            if(a.intValue()==el){
                broj++;
            }
        }
        return broj;
    }
    public void removeDuplicates(){
        for(int i=0;i<lista.size();i++){
            Integer element = lista.get(i);
            for(int j=i;j<lista.size();j++){
                if(lista.get(j).equals(element)){
                    lista.remove(j);
                }
            }
        }
    }
    public int sumFirst(int k){
        int suma=0;
        for(int i=0;i<k;i++){
            suma+=lista.get(i);
        }
        return suma;
    }
    public int sumLast(int k){
        int suma=0;
        for(int i=lista.size();i>lista.size()-k;i--){
            suma+=lista.get(i);
        }
        return suma;
    }
    public void shiftRight(int idx, int k){
        int index = (idx + k % lista.size());
        Integer element = lista.remove(idx);
        lista.add(index,element);
    }
    public void shiftLeft(int idx , int k){
        int index = (idx - k) % lista.size();
        Integer element = lista.remove(idx);
        if(index<0){
            index = lista.size() + index;
            lista.add(index,element);
        }
    }
    public IntegerList addValue(int value){
        IntegerList a = new IntegerList(lista.size());
        for(int i=0;i<lista.size();i++){
            a.add(lista.get(i)+value,i);
        }
        return a;
    }
}
public class IntegerListTest {
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) { //test standard methods
            int subtest = jin.nextInt();
            if ( subtest == 0 ) {
                IntegerList list = new IntegerList();
                while ( true ) {
                    int num = jin.nextInt();
                    if ( num == 0 ) {
                        list.add(jin.nextInt(), jin.nextInt());
                    }
                    if ( num == 1 ) {
                        list.remove(jin.nextInt());
                    }
                    if ( num == 2 ) {
                        print(list);
                    }
                    if ( num == 3 ) {
                        break;
                    }
                }
            }
            if ( subtest == 1 ) {
                int n = jin.nextInt();
                Integer a[] = new Integer[n];
                for ( int i = 0 ; i < n ; ++i ) {
                    a[i] = jin.nextInt();
                }
                IntegerList list = new IntegerList(a);
                print(list);
            }
        }
        if ( k == 1 ) { //test count,remove duplicates, addValue
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    System.out.println(list.count(jin.nextInt()));
                }
                if ( num == 1 ) {
                    list.removeDuplicates();
                }
                if ( num == 2 ) {
                    print(list.addValue(jin.nextInt()));
                }
                if ( num == 3 ) {
                    list.add(jin.nextInt(), jin.nextInt());
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
        if ( k == 2 ) { //test shiftRight, shiftLeft, sumFirst , sumLast
            int n = jin.nextInt();
            Integer a[] = new Integer[n];
            for ( int i = 0 ; i < n ; ++i ) {
                a[i] = jin.nextInt();
            }
            IntegerList list = new IntegerList(a);
            while ( true ) {
                int num = jin.nextInt();
                if ( num == 0 ) { //count
                    list.shiftLeft(jin.nextInt(), jin.nextInt());
                }
                if ( num == 1 ) {
                    list.shiftRight(jin.nextInt(), jin.nextInt());
                }
                if ( num == 2 ) {
                    System.out.println(list.sumFirst(jin.nextInt()));
                }
                if ( num == 3 ) {
                    System.out.println(list.sumLast(jin.nextInt()));
                }
                if ( num == 4 ) {
                    print(list);
                }
                if ( num == 5 ) {
                    break;
                }
            }
        }
    }

    public static void print(IntegerList il) {
        if ( il.size() == 0 ) System.out.print("EMPTY");
        for ( int i = 0 ; i < il.size() ; ++i ) {
            if ( i > 0 ) System.out.print(" ");
            System.out.print(il.get(i));
        }
        System.out.println();
    }

}


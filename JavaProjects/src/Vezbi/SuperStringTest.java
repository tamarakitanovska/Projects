package Vezbi;

import java.util.*;

class SuperString{
    LinkedList<String> list;
    Stack<String> orderedStrings;
    public SuperString(){
        list = new LinkedList<>();
        orderedStrings = new Stack<>();
    }
    public  void append(String s){
        list.add(s);
        orderedStrings.push(s);
    }
    public void insert(String s){
        list.add(0,s);
        orderedStrings.push(s);
    }
    public boolean contains(String s){
        return list.contains(s);
    }
    public String reverseString(String s){
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
    public void reverse(){
        Collections.reverse(list);
        LinkedList<String> newlist = new LinkedList<>();
        ListIterator<String> iterator = (ListIterator<String>) list.iterator();
        while(iterator.hasNext()){
            String toReverse = iterator.next();
            toReverse = reverseString(toReverse);
            newlist.add(toReverse);
        }
        this.list=newlist;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        ListIterator<String> iterator = (ListIterator<String>) list.iterator();
        while (iterator.hasNext()){
            sb.append(iterator.next());
        }
        return sb.toString();
    }
    public void removeLast(int k){
        for(int i=0;i<k;i++){
            String current = orderedStrings.pop();
            String currentReverse = reverseString(current);
            list.remove(current);
            list.remove(currentReverse);
        }
    }
}
public class SuperStringTest {
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (  k == 0 ) {
            SuperString s = new SuperString();
            while ( true ) {
                int command = jin.nextInt();
                if ( command == 0 ) {//append(String s)
                    s.append(jin.next());
                }
                if ( command == 1 ) {//insert(String s)
                    s.insert(jin.next());
                }
                if ( command == 2 ) {//contains(String s)
                    System.out.println(s.contains(jin.next()));
                }
                if ( command == 3 ) {//reverse()
                    s.reverse();
                }
                if ( command == 4 ) {//toString()
                    System.out.println(s);
                }
                if ( command == 5 ) {//removeLast(int k)
                    s.removeLast(jin.nextInt());
                }
                if ( command == 6 ) {//end
                    break;
                }
            }
        }
    }

}


package Vezbi;

import java.util.ArrayList;
import java.util.Scanner;
interface Item{
    public int getPrice();
    public String getTupe();
}
class PizzaItem implements Item{
    public String type;
    public PizzaItem(String type) throws InvalidPizzaTypeException {
        if(type.equals("Standard") || type.equals("Pepperoni") || type.equals("Vegetarian")){
            this.type=type;
        }
        else{
            throw new InvalidPizzaTypeException();
        }
    }

    @Override
    public int getPrice() {
        if(this.type.equals("Standard")){
            return 10;
        }
        else if(this.type.equals("Pepperoni")){
            return 12;
        }
        else
            return 8;
    }

    @Override
    public String getTupe() {
        if(this.type.equals("Standard")){
            return "Standard";
        }
        else if(this.type.equals("Pepperoni")){
            return "Pepperoni";
        }
        else
            return "Vegetarian";
    }
}
class ExtraItem implements Item{
    String type;
    public ExtraItem(String type) throws InvalidExtraTypeException {
        if(type.equals("Ketchup") || type.equals("Coke")){
            this.type=type;
        }
        else {
            throw new InvalidExtraTypeException();
        }
    }

    @Override
    public int getPrice() {
        if(this.type.equals("Ketchup")){
            return 3;
        }
        else return 5;
    }

    @Override
    public String getTupe() {
        if(this.type.equals("Ketchup"))
            return "Ketchup";
        else
            return "Coke";
    }
}
class Order{
    ArrayList<Item> items;
    ArrayList<Integer> kolicina;
    private boolean lock =false;
    public Order(){
        items=new ArrayList<>();
        kolicina = new ArrayList<>();
    }
    public void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
        if(lock){
            throw new OrderLockedException();
        }
        boolean flag = false;
        if(count>10){
            throw new ItemOutOfStockException(item);
        }
        for(int i=0;i<items.size();i++){
            if(items.get(i).getTupe().equals(item.getTupe())){
                kolicina.set(i,count);
                flag=true;
            }
        }
        if(flag==false) {
            items.add(item);
            kolicina.add(count);
        }
    }
    public int getPrice(){
        int suma=0;
        for(int i=0;i<items.size();i++){
            suma+=items.get(i).getPrice()*kolicina.get(i);
        }
        return suma;
    }
    public void displayOrder(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<items.size();i++){
            sb.append(String.format("%3d.%-15sx%2d%5d$\n",i+1,items.get(i).getTupe(),kolicina.get(i),items.get(i).getPrice()*kolicina.get(i)));
        }
        sb.append(String.format("%-22s%d$","Total:", getPrice()));
        System.out.println(sb.toString());
    }
    public void removeItem(int idx) throws OrderLockedException {
        if(lock){
            throw new OrderLockedException();
        }
        if(idx<0 || idx > items.size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        items.remove(idx);
        kolicina.remove(idx);
    }
    public void lock() throws EmptyOrderException {
        if(items.size()>=1){
            lock=true;
        }
        else{
            throw new EmptyOrderException();
        }
    }
}
class OrderLockedException extends Exception{
    public OrderLockedException(){
        super();
    }
}
class EmptyOrderException extends Exception{
    public EmptyOrderException(){
        super();
    }
}
class ItemOutOfStockException extends Exception{
    Item item;
    public ItemOutOfStockException(Item item){
        super();
        this.item=item;
    }
}
class InvalidExtraTypeException extends Exception{
    public InvalidExtraTypeException(){
        super();
    }
}
class InvalidPizzaTypeException extends Exception{
    public InvalidPizzaTypeException() {
        super();
    }
}
public class PizzaOrderTest {
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) { //test Item
            try {
                String type = jin.next();
                String name = jin.next();
                Item item = null;
                if (type.equals("Pizza")) item = new PizzaItem(name);
                else item = new ExtraItem(name);
                System.out.println(item.getPrice());
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
        if (k == 1) { // test simple order
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 2) { // test order with removing
            Order order = new Order();
            while (true) {
                try {
                    String type = jin.next();
                    String name = jin.next();
                    Item item = null;
                    if (type.equals("Pizza")) item = new PizzaItem(name);
                    else item = new ExtraItem(name);
                    if (!jin.hasNextInt()) break;
                    order.addItem(item, jin.nextInt());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            jin.next();
            System.out.println(order.getPrice());
            order.displayOrder();
            while (jin.hasNextInt()) {
                try {
                    int idx = jin.nextInt();
                    order.removeItem(idx);
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println(order.getPrice());
            order.displayOrder();
        }
        if (k == 3) { //test locking & exceptions
            Order order = new Order();
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.addItem(new ExtraItem("Coke"), 1);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.lock();
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                order.removeItem(0);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName());
            }
        }
    }
}


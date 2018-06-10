package Vezbi;

import java.util.*;
import java.util.stream.Collectors;

class Component{
    String color;
    int weight;
    TreeSet<Component> components;
    public Component(String color, int weight){
        this.color = color;
        this.weight = weight;
        components = new TreeSet<>(Comparator.comparing(Component::getWeight).thenComparing(Component::getColor));
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }
    void addComponent(Component component){

        components.add(component);
    }
    public String getString(String s){
        s+=getWeight()+":"+getColor()+"\n";
        if(components.size()!=0){
            String finalS = s;
            return components.stream()
                    .map(m->m.getString(finalS))
                    .collect(Collectors.joining(""));
        }
        return s;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

class Window{
    String name;
    HashMap<Integer,Component> components;
    public Window(String name){
        this.name=name;
        components = new HashMap<>();
    }
    void addComponent(int position, Component component) throws InvalidPositionException {
        if(components.containsKey(position)){
            throw new InvalidPositionException("Invalid position "+ position+ ", alredy taken!");
        }
        else {
            components.put(position, component);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        System.out.println(getName());
         String s = components.entrySet().stream()
                 .map(c->{
                     return c.getKey() + ":" + c.getValue().getString("");
                 })
                 .collect(Collectors.joining());
         return s;
    }
    void changeColor(int weight, String color){
        double average;
        average = components.values().stream()
                .flatMap(x->x.components.stream())
                .mapToDouble(Component::getWeight)
                .average()
                .getAsDouble();
                components.values().stream()
                .flatMap(x->x.components.stream())
                .forEach(x->{
                    if(x.getWeight()<weight){
                        x.setColor(color);
                    }
                });
    }
    void swichComponents(int pos1, int pos2){
        Component c = components.get(pos1);
        components.put(pos1,components.get(pos2));
        components.put(pos2,c);
    }

}
class InvalidPositionException extends Exception{
    public InvalidPositionException(String message){
        super(message);
    }
}

public class ComponentTest {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Window window = new Window(name);
        Component prev = null;
        while (true) {
            try {
                int what = scanner.nextInt();
                scanner.nextLine();
                if (what == 0) {
                    int position = scanner.nextInt();
                    window.addComponent(position, prev);
                } else if (what == 1) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev = component;
                } else if (what == 2) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                    prev = component;
                } else if (what == 3) {
                    String color = scanner.nextLine();
                    int weight = scanner.nextInt();
                    Component component = new Component(color, weight);
                    prev.addComponent(component);
                } else if(what == 4) {
                    break;
                }

            } catch (InvalidPositionException e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
        }

        System.out.println("=== ORIGINAL WINDOW ===");
        System.out.println(window);
        int weight = scanner.nextInt();
        scanner.nextLine();
        String color = scanner.nextLine();
        window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
        System.out.println(window);
        int pos1 = scanner.nextInt();
        int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
        window.swichComponents(pos1, pos2);
        System.out.println(window);
    }
    }

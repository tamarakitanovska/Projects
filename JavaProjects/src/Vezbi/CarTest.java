package Vezbi;

import java.util.*;
import java.util.stream.Collectors;

class Car{
    String manufacturer;
    String model;
    int price;
    float power;
    public Car(String manufacturer, String model, int price, float power){
        this.manufacturer=manufacturer;
        this.model=model;
        this.price=price;
        this.power=power;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public float getPower() {
        return power;
    }
}
class SetOfCars{
    TreeSet<Car> setCars;
    public SetOfCars(){
        setCars = new TreeSet<>();
    }
    public void addCar(String manufacturer, String model, int price, float power){
        setCars.add(new Car(manufacturer,model,price,power));
    }
        }
class CarCollection{
    HashMap<String,SetOfCars> hashCar;
    public CarCollection(){
        hashCar = new HashMap<>();
    }
    public void addCar(Car car){
        SetOfCars s = hashCar.computeIfAbsent(car.manufacturer,k->new SetOfCars());
        s.addCar(car.manufacturer,car.model,car.price,car.power);
    }
    public void sortByPrice(boolean ascending){
        if(ascending){
            hashCar.values().stream()
                    .flatMap(x->{
                        return x.setCars.stream();
                    })
                    .sorted(Comparator.comparing(Car::getPrice).thenComparing(Car::getPower));
        }
        else{
            hashCar.values().stream()
                    .flatMap(x->{
                        return x.setCars.stream();
                    })
                    .sorted(Comparator.comparing(Car::getPrice).reversed().thenComparing(Car::getPower));
        }
    }
    public List<Car> filterByManufacturer(String manufacturer){
        SetOfCars s = hashCar.get(manufacturer.toLowerCase());
        return s.setCars.stream()
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }
    public List<Car> getList(){
        return hashCar.values().stream()
                .flatMap(x->{
                    return x.setCars.stream();
                })
                .collect(Collectors.toList());
    }

}
public class CarTest {
    public static void main(String[] args) {
        CarCollection carCollection = new CarCollection();
        String manufacturer = fillCollection(carCollection);
        carCollection.sortByPrice(true);
        System.out.println("=== Sorted By Price ASC ===");
        print(carCollection.getList());
        carCollection.sortByPrice(false);
        System.out.println("=== Sorted By Price DESC ===");
        print(carCollection.getList());
        System.out.printf("=== Filtered By Manufacturer: %s ===\n", manufacturer);
        List<Car> result = carCollection.filterByManufacturer(manufacturer);
        print(result);
    }

    static void print(List<Car> cars) {
        for (Car c : cars) {
            System.out.println(c);
        }
    }

    static String fillCollection(CarCollection cc) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            if(parts.length < 4) return parts[0];
            Car car = new Car(parts[0], parts[1], Integer.parseInt(parts[2]),
                    Float.parseFloat(parts[3]));
            cc.addCar(car);
        }
        scanner.close();
        return "";
    }
}

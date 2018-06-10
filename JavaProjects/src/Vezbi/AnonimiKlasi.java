package Vezbi;
interface Multiplication{
    int Multiplate(int a, int b);
}
class Dog implements Multiplication{

    @Override
    public int Multiplate(int a, int b) {
        return a+b;
    }
}
interface HelloWorld{
    void Greeting(String a);
}
class EnglishGreeting implements HelloWorld{

    @Override
    public void Greeting(String a) {
        System.out.println(a);
    }
}
class Person implements Multiplication{
    @Override
    public int Multiplate(int a, int b) {
        return a*b;
    }
}
public class AnonimiKlasi {
    interface SayHello{
        void Greeting();
    }
    interface Addition{
        int Add();
    }
    public static void main(String[] args) throws DivisionByZeroException{
        SayHello english = new SayHello() {
            @Override
            public void Greeting() {
                System.out.println( "English: Hello World!");
            }
        };
        SayHello french = new SayHello() {
            @Override
            public void Greeting() {
                System.out.printf("French: Bonjor le monde!\n");
            }
        };
        SayHello spanish = new SayHello() {
            @Override
            public void Greeting() {
                System.out.println("Spanish: Holla Mundo!");
            }
        };
        english.Greeting();
        spanish.Greeting();
        french.Greeting();
        Person person = new Person();
        System.out.println(person.Multiplate(2,2));
        Dog dog = new Dog();
        System.out.println(dog.Multiplate(3,6));


        Multiplication multiplication = new Multiplication() {
            @Override
            public int Multiplate(int a, int b) {
                return a-b;
            }
        };

        System.out.println(multiplication.Multiplate(5,1));
        HelloWorld englishGreeting = new EnglishGreeting();
        englishGreeting.Greeting("Hello world");

        MathOpertion addition = (int a,int b) -> a+b;
        MathOpertion substraction = (int a, int b) -> a-b;
        MathOpertion multiplication1 = (int a, int b) -> a*b;
        MathOpertion dividion = (int a, int b) -> a/b;
        System.out.println("Adition: " + addition.operation(4,3));
        System.out.println("Substraction: "+ substraction.operation(4,2));
        System.out.println("Multiplication: "+ multiplication1.operation(3,2));
        System.out.println("Dividion: "+ dividion.operation(8,4));
        try{
            int sum=0;
            sum=5/0;
            throw  new DivisionByZeroException();
        }

        catch (Exception ex){
            String message = ex.getMessage();
            System.out.println("Poraka: "+ message);
            System.exit(0);
        }
    }


}
interface MathOpertion{
    int operation(int a, int b);
}
class DivisionByZeroException extends Exception{
    public DivisionByZeroException(){
        super("Division by zero");
    }
    public DivisionByZeroException(String message){
        super(message);
    }
}

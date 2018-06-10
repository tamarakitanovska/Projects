package Vezbi;

import java.io.*;
import java.util.*;

class Contact implements Comparable<Contact>{
    private String name;
    private String[] phonenumber;
    public Contact(String name, String[] phonenumber) throws InvalidNameException, MaximumSizeExceddedException, InvalidNumberException {
        if(!validName(name)){
            throw new InvalidNameException();
        }
        this.name=name;
        if(phonenumber.length>5){
            throw new MaximumSizeExceddedException();
        }
        for(int i=0;i<phonenumber.length;i++){
            if(!validNumber(phonenumber[i])){
                throw new InvalidNumberException();
            }
        }
        this.phonenumber=phonenumber;
    }
    public Contact(String name) throws InvalidNameException {
        if(!validName(name)){
            throw new InvalidNameException();
        }
        this.name=name;
    }
    public static boolean validName(String name){
        if(name.length()<4 || name.length()>10){
            return false;
        }
        for(int i=0;i<name.length();i++){
            if(!Character.isAlphabetic(name.charAt(i)) || !Character.isDigit(name.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public static boolean validNumber(String s){
        if(s.length()!=9){
            return false;
        }
        if(!s.substring(0,3).equals("070") || !s.substring(0,3).equals("071") || !s.substring(0,3).equals("072") ||
                !s.substring(0,3).equals("075") || !s.substring(0,3).equals("076") || !s.substring(0,3).equals("077")
                || !s.substring(0,3).equals("078")){
            return false;
        }
        for(int i=0;i<s.length();i++){
            if(!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public String[] getPhonenumber() {
        return phonenumber;
    }
    public void addNumber(String p) {
        String[] phonenumber1 = new String[phonenumber.length+1];
        for(int i=0;i<phonenumber1.length;i++){
            phonenumber1[i]=phonenumber[i];
        }
        phonenumber1[phonenumber1.length-1] = p;
        phonenumber=phonenumber1;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("\n");
        sb.append(phonenumber.length);
        sb.append("\n");
        for(int i=0;i<phonenumber.length;i++){
            sb.append(phonenumber[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
    public static Contact valueOf(String s) throws InvalidFormatException, InvalidNumberException, InvalidNameException, MaximumSizeExceddedException {
        String[] contact = s.split("\\s+");
        if(contact.length==0){
            throw new InvalidFormatException();
        }
        else if(contact.length==1){
            return new Contact(contact[0]);
        }
        else if(contact.length==2){
            return new Contact(contact[0], new String[] {contact[1]});
        }
        else if(contact.length==3){
            return new Contact(contact[0], new String[] {contact[1], contact[2]});
        }
        else if(contact.length==4){
            return new Contact(contact[0], new String[] {contact[1],contact[2],contact[3]});
        }
        else if(contact.length==5){
            return new Contact(contact[0], new String[] {contact[1],contact[2],contact[3],contact[4]});
        }
        else if(contact.length==6){
            return new Contact(contact[0], new String[] {contact[1],contact[2],contact[3],contact[4],contact[5]});
        }
        else{
            throw new InvalidFormatException();
        }
    }

    @Override
    public int compareTo(Contact o) {
        return this.name.compareTo(o.name);
    }

    public String[] getNumbers() {
        return phonenumber;
    }
}
class PhoneBook{
    private ArrayList<Contact> list;
    private int number;
    public PhoneBook(){
        list= new ArrayList<>();
    }
    public void addContact(Contact contact) throws MaximumSizeExceddedException, InvalidNameException {
        if(list.size()>250){
            throw new MaximumSizeExceddedException();
        }
        for(Contact a : list){
            if(a.getName().equals(contact.getName())){
                throw new InvalidNameException();
            }
        }
        list.add(contact);
        number++;
    }
    public Contact getContactForName(String name){
        for(Contact a : list){
            if(a.getName()==name){
                return a;
            }
        }
        return null;
    }
    public int numberOfContacts(){
        return number;
    }
    public Contact[] getContacts(){
        Collections.sort(list);
        Contact[] niza = new Contact[list.size()];
        int i=0;
        for(Contact c : list){
            niza[i++]=c;
        }
        return niza;
    }
    public boolean removeContact(String name){
        for(Contact a : list){
            if(a.getName().equals(name)){
                list.remove(a);
                return true;
            }
        }
        return false;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Contact a : list){
            sb.append(a);
            sb.append("\n");
        }
        return sb.toString();
    }
    public static boolean saveAsTextFile(PhoneBook phonebook,String path) throws FileNotFoundException {
        PrintWriter p = null;
        p = new PrintWriter(new FileOutputStream(path));
        if(p==null){
            return false;
        }
        p.println(phonebook);
        p.flush();
        p.close();
        return true;
    }
    static PhoneBook loadFromTextFile(String path) throws NumberFormatException, IOException, InvalidNameException, MaximumSizeExceddedException {
        BufferedReader bf=new BufferedReader(new FileReader(path));

        if(bf==null)
            throw new IOException();
        PhoneBook newPhoneBook=new PhoneBook();
        String []numbers=null;
        boolean flagName=false, flagNumber=false, flagPhoneNumbers=false;


        String line=null;
        Contact a=null;
        while((line=bf.readLine())!=null)
        {
            if(!flagName)
            {
                flagName=true;
                a=new Contact(line);
            }
            else if(!flagNumber){
                numbers=new String[Integer.parseInt(line)];
                flagNumber=true;
            }

            else{
                if(!Character.isDigit(line.charAt(1)))
                {
                    flagNumber=false;
                    newPhoneBook.addContact(a);
                    a=new Contact(line);
                }
                else{
                    a.addNumber(line);
                }

            }
        }

        return newPhoneBook;
    }
    public Contact[] getContactsForNumber(String number_prefix){

        int length=number_prefix.length();
        ArrayList<Contact> a=new ArrayList<>(10);

        for(Contact contact:list)
        {
            String []s=contact.getNumbers();
            for(int j=0;j<s.length;++j)
            {
                if(number_prefix.equals(s[j].substring(0,length)))
                    a.add(contact);
            }
        }

        int i=0;
        Contact []s=new Contact[a.size()];
        for(Contact contact:a)
            s[i++]=contact;
        return s;
    }
}
class InvalidFormatException extends Exception{
    public InvalidFormatException(){
        super();
    }
}
class MaximumSizeExceddedException extends Exception{
    public MaximumSizeExceddedException(){
        super();
    }
}
class InvalidNumberException extends Exception{
    public InvalidNumberException(){
        super();
    }
}
class InvalidNameException extends Exception{
    public InvalidNameException(){
        super();
    }
}
public class ContactTest {
    public static void main(String[] args) throws Exception {
        Scanner jin = new Scanner(System.in);
        String line = jin.nextLine();
        switch( line ) {
            case "test_contact":
                testContact(jin);
                break;
            case "test_phonebook_exceptions":
                testPhonebookExceptions(jin);
                break;
            case "test_usage":
                testUsage(jin);
                break;
        }
    }

    private static void testFile(Scanner jin) throws Exception {
        PhoneBook phonebook = new PhoneBook();
        while ( jin.hasNextLine() )
            phonebook.addContact(new Contact(jin.nextLine(),jin.nextLine().split("\\s++")));
        String text_file = "phonebook.txt";
        PhoneBook.saveAsTextFile(phonebook,text_file);
        PhoneBook pb = PhoneBook.loadFromTextFile(text_file);
        if ( ! pb.equals(phonebook) ) System.out.println("Your file saving and loading doesn't seem to work right");
        else System.out.println("Your file saving and loading works great. Good job!");
    }

    private static void testUsage(Scanner jin) throws Exception {
        PhoneBook phonebook = new PhoneBook();
        while ( jin.hasNextLine() ) {
            String command = jin.nextLine();
            switch ( command ) {
                case "add":
                    phonebook.addContact(new Contact(jin.nextLine(),jin.nextLine().split("\\s++")));
                    break;
                case "remove":
                    phonebook.removeContact(jin.nextLine());
                    break;
                case "print":
                    System.out.println(phonebook.numberOfContacts());
                    System.out.println(Arrays.toString(phonebook.getContacts()));
                    System.out.println(phonebook.toString());
                    break;
                case "get_name":
                    System.out.println(phonebook.getContactForName(jin.nextLine()));
                    break;
                case "get_number":
                    System.out.println(Arrays.toString(phonebook.getContactsForNumber(jin.nextLine())));
                    break;
            }
        }
    }

    private static void testPhonebookExceptions(Scanner jin) {
        PhoneBook phonebook = new PhoneBook();
        boolean exception_thrown = false;
        try {
            while ( jin.hasNextLine() ) {
                phonebook.addContact(new Contact(jin.nextLine()));
            }
        }
        catch ( InvalidNameException e ) {
            //System.out.println(e.name);
            exception_thrown = true;
        }
        catch ( Exception e ) {}
        if ( ! exception_thrown ) System.out.println("Your addContact method doesn't throw InvalidNameException");
        /*
		exception_thrown = false;
		try {
		phonebook.addContact(new Contact(jin.nextLine()));
		} catch ( MaximumSizeExceddedException e ) {
			exception_thrown = true;
		}
		catch ( Exception e ) {}
		if ( ! exception_thrown ) System.out.println("Your addContact method doesn't throw MaximumSizeExcededException");
        */
    }
    private static void testContact(Scanner jin) throws Exception{
        boolean exception_thrown = true;
        String names_to_test[] = { "And\nrej","asd","AAAAAAAAAAAAAAAAAAAAAA","Ã�ï¿½Ã�Â½Ã�Â´Ã‘â‚¬Ã�ÂµÃ‘ËœA123213","Andrej#","Andrej<3"};
        for ( String name : names_to_test ) {
            try {
                new Contact(name);
                exception_thrown = false;
            } catch (InvalidNameException e) {
                exception_thrown = true;
            }

            if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw an InvalidNameException");
        }
        String numbers_to_test[] = { "+071718028","number","078asdasdasd","070asdqwe","070a56798","07045678a","123456789","074456798","073456798","079456798" };
        for ( String number : numbers_to_test ) {
            try {
                new Contact("Andrej",new String []{number});
                exception_thrown = false;
            } catch (InvalidNumberException e) {
                exception_thrown = true;
            } catch (InvalidNameException e) {
                e.printStackTrace();
            } catch (MaximumSizeExceddedException e) {
                e.printStackTrace();
            }
            if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw an InvalidNumberException");
        }
        String nums[] = new String[10];
        for ( int i = 0 ; i < nums.length ; ++i ) nums[i] = getRandomLegitNumber();
        try {
            new Contact("Andrej",nums);
            exception_thrown = false;
        } catch (MaximumSizeExceddedException e) {
            exception_thrown = true;
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
        if ( ! exception_thrown ) System.out.println("Your Contact constructor doesn't throw a MaximumSizeExceddedException");
        Random rnd = new Random(5);
        Contact contact = null;
        try {
            contact = new Contact("Andrej",new String[]{getRandomLegitNumber(rnd),getRandomLegitNumber(rnd),getRandomLegitNumber(rnd)});

        } catch (InvalidNameException e) {
            e.printStackTrace();
        } catch (MaximumSizeExceddedException e) {
            e.printStackTrace();
        } catch (InvalidNumberException e) {
            e.printStackTrace();
        }
        System.out.println(contact.getName());
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
        contact.addNumber(getRandomLegitNumber(rnd));
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
        contact.addNumber(getRandomLegitNumber(rnd));
        System.out.println(Arrays.toString(contact.getNumbers()));
        System.out.println(contact.toString());
    }

    static String[] legit_prefixes = {"070","071","072","075","076","077","078"};
    static Random rnd = new Random();

    private static String getRandomLegitNumber() {
        return getRandomLegitNumber(rnd);
    }

    private static String getRandomLegitNumber(Random rnd) {
        StringBuilder sb = new StringBuilder(legit_prefixes[rnd.nextInt(legit_prefixes.length)]);
        for ( int i = 3 ; i < 9 ; ++i )
            sb.append(rnd.nextInt(10));
        return sb.toString();
    }
}



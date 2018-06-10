package Vezbi;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Book{
    String title;
    String category;
    float price;
    public Book(String title, String category, float price){
        this.title=title;
        this.category=category;
        this.price=price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }
    public String toString(){ return title + " " + price;
    }
}
class SetOfBooks{
    TreeSet<Book> books;
    public SetOfBooks(){
        books = new TreeSet<>(Comparator.comparing(Book::getCategory).thenComparing(Book::getPrice).reversed());
    }
    public void AddBook(Book book){
        books.add(book);
    }
}
class BookCollection{
     HashMap<String,SetOfBooks> hashBooks;
     public BookCollection(){
        hashBooks = new HashMap<>();
     }
     public void addBook(Book book){
         SetOfBooks s = hashBooks.computeIfAbsent(book.category,k->new SetOfBooks());
         s.AddBook(book);
     }
     public void printByCategory(String category){
         SetOfBooks s = hashBooks.get(category);
         s.books.stream()
                 .forEach(System.out::println);
     }
     public List<Book> getCheapestN(int n){
         return hashBooks.entrySet().stream()
                 .flatMap(x->x.getValue().books.stream())
                 .sorted(Comparator.comparing(Book::getPrice))
                 .limit(n)
                 .collect(Collectors.toList());
     }
}
public class BooksTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        BookCollection booksCollection = new BookCollection();
        Set<String> categories = fillCollection(scanner, booksCollection);
        System.out.println("=== PRINT BY CATEGORY ===");
        for (String category : categories) {
            System.out.println("CATEGORY: " + category);
            booksCollection.printByCategory(category);
        }
        System.out.println("=== TOP N BY PRICE ===");
        print(booksCollection.getCheapestN(n));
    }

    static void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner,
                                          BookCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
            collection.addBook(book);
            categories.add(parts[1]);
        }
        return categories;
    }
}

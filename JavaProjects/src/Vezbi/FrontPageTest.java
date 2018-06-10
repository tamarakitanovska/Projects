package Vezbi;

import java.util.*;

class Category{
    String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
abstract class NewsItem{
    String title;
    Date date;
    Category category;

    public NewsItem(String title, Date date, Category category) {
        this.title = title;
        this.date = date;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
    abstract public String getTeaser();
}
class TextNewsItem extends NewsItem{

    String text;
    public TextNewsItem(String title, Date date, Category category,String text) {
        super(title, date, category);
        this.text=text;
    }

    @Override
    public String getTeaser() {
        StringBuilder sb = new StringBuilder();
        sb.append(title + "\n" + date+"\n");
        if(text.length()>80) {
            sb.append(text.substring(0, 80)+"\n");
        }
        else {
            sb.append(text+"\n");
        }
        return sb.toString();
    }
}
class MediaNewsItem extends NewsItem{

    String url;
    int views;
    public MediaNewsItem(String title, Date date, Category category,String url, int views) {
        super(title, date, category);
        this.url=url;
        this.views=views;
    }

    @Override
    public String getTeaser() {
        StringBuilder sb = new StringBuilder();
        sb.append(title + "\n" + date + "\n" + url + "\n" + views + "\n");
        return sb.toString();
    }
}
class FrontPage{
    ArrayList<NewsItem> list;
    Category[] categories;

    public FrontPage(Category[] categories) {
        list = new ArrayList<>();
        this.categories = categories;
    }
    public void addNewsItem(NewsItem newsItem){
        list.add(newsItem);
    }
    List<NewsItem> listByCategory(Category category){
      List<NewsItem> news = new ArrayList();
        for (NewsItem a: list) {
            if(a.getCategory().equals(category)){
                news.add(a);
            }
        }
        return news;
    }
    public List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
        List<NewsItem> news = new ArrayList<>();
        for (NewsItem a: list) {
            if(a.getCategory().getName().equals(category)){
                news.add(a);
            }
            else{
                throw new CategoryNotFoundException(category);
            }
        }
        return news;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (NewsItem a: list) {
            sb.append(a.getTeaser() + "\n");
        }
        return sb.toString();
    }
}
class CategoryNotFoundException extends Exception{
    public CategoryNotFoundException(String name){
        super(name);
    }
}
public class FrontPageTest {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        Category[] categories = new Category[parts.length];
        for (int i = 0; i < categories.length; ++i) {
            categories[i] = new Category(parts[i]);
        }
        int n = scanner.nextInt();
        scanner.nextLine();
        FrontPage frontPage = new FrontPage(categories);
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            cal = Calendar.getInstance();
            int min = scanner.nextInt();
            cal.add(Calendar.MINUTE, -min);
            Date date = cal.getTime();
            scanner.nextLine();
            String text = scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
            frontPage.addNewsItem(tni);
        }

        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int min = scanner.nextInt();
            cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -min);
            scanner.nextLine();
            Date date = cal.getTime();
            String url = scanner.nextLine();
            int views = scanner.nextInt();
            scanner.nextLine();
            int categoryIndex = scanner.nextInt();
            scanner.nextLine();
            MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
            frontPage.addNewsItem(mni);
        }
        // Execution
        String category = scanner.nextLine();
        System.out.println(frontPage);
        for(Category c : categories) {
            System.out.println(frontPage.listByCategory(c).size());
        }
        try {
            System.out.println(frontPage.listByCategoryName(category).size());
        } catch(CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

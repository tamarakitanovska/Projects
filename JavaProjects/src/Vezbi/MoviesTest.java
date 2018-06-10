package Vezbi;

import java.util.*;
import java.util.stream.Collectors;

class Movie{
    String name;
    TreeSet<Integer> ratings;
    public Movie(String name){
        this.name=name;
        ratings = new TreeSet<Integer>(Comparator.comparing(Integer::intValue));
    }
    public void setRatings(int[] ratings){
        for (int a: ratings) {
            this.ratings.add(a);
        }
    }
    public double presmetajProsecenRejting(){
        return ratings.stream()
                .mapToDouble(x->{
                    return x.doubleValue();
                })
                .average()
                .getAsDouble();
    }

    public double presmetajNajdobarKoeficient(){
        int max = ratings.first();
        return presmetajProsecenRejting()*ratings.size()/max;
    }

    public String getName() {
        return name;
    }
}
class MoviesList{
    HashSet<Movie> hashMovie;
    public MoviesList(){
        hashMovie = new HashSet<>();
    }
    public void addMovie(String title, int[] ratings){
        Movie m = new Movie(title);
        hashMovie.add(m);
        m.setRatings(ratings);
    }
    public List<Movie> top10ByAvgRating(){
        return hashMovie.stream()
                .sorted(Comparator.comparing(Movie::presmetajProsecenRejting).reversed().thenComparing(Movie::getName))
                .limit(10)
                .collect(Collectors.toList());
    }
    public List<Movie> top10ByRatingCoef(){
        return hashMovie.stream()
                .sorted(Comparator.comparing(Movie::presmetajNajdobarKoeficient))
                .limit(10)
                .collect(Collectors.toList());
    }

}
public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoviesList moviesList = new MoviesList();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int x = scanner.nextInt();
            int[] ratings = new int[x];
            for (int j = 0; j < x; ++j) {
                ratings[j] = scanner.nextInt();
            }
            scanner.nextLine();
            moviesList.addMovie(title, ratings);
        }
        scanner.close();
        List<Movie> movies = moviesList.top10ByAvgRating();
        System.out.println("=== TOP 10 BY AVERAGE RATING ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        movies = moviesList.top10ByRatingCoef();
        System.out.println("=== TOP 10 BY RATING COEFFICIENT ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}

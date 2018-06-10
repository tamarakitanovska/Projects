package Vezbi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Team{
    String name;
    int brNatprevari;
    int brPobedi;
    int brNereseni;
    int brLoses;
    int goalsPrimeni;
    int goalsDadeni;
    public Team(String name){
        this.name=name;
        brNatprevari=0;
        brPobedi=0;
        brNereseni=0;
        brLoses=0;
        goalsPrimeni=0;
        goalsDadeni=0;
    }

    public String getName() {
        return name;
    }

    public int getBrNatprevari() {
        return brNatprevari;
    }

    public int getBrPobedi() {
        return brPobedi;
    }

    public int getBrNereseni() {
        return brNereseni;
    }

    public int getBrLoses() {
        return brLoses;
    }
    public int presmetajPoeni(){
        return brPobedi*3+brNereseni*1;
    }
    public String toString(){
        return String.format("%-15s%5d%5d%5d%5d%5d",getName(),brNatprevari,brPobedi,brNereseni,getBrLoses(),presmetajPoeni());
    }
    public int getGoals(){
        return goalsDadeni-goalsPrimeni;
    }
}
class FootballTable{
    HashMap<String,Team> list;
    public FootballTable(){
        list = new HashMap<>();
    }
    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals){
        Team teamH = list.computeIfAbsent(homeTeam,k->new Team(homeTeam));
        Team teamA = list.computeIfAbsent(awayTeam, k->new Team(awayTeam));

        if(homeGoals>awayGoals){
            //home team pobeduva
            teamH.brPobedi++;
            teamH.brNatprevari++;
            teamA.brNatprevari++;
            teamH.goalsDadeni+=homeGoals;
            teamA.goalsDadeni+=awayGoals;
            teamH.goalsPrimeni+=awayGoals;
            teamA.goalsDadeni+=homeGoals;
            teamA.brLoses++;
        }
        else if(homeGoals==awayGoals){
            //remi
            teamH.brNereseni++;
            teamA.brNereseni++;
            teamH.brNatprevari++;
            teamH.goalsDadeni+=homeGoals;
            teamA.goalsDadeni+=awayGoals;
            teamA.brNatprevari++;
            teamH.goalsPrimeni+=awayGoals;
            teamA.goalsPrimeni+=homeGoals;
        }
        else{
            //away team pobeduva
            teamA.brPobedi++;
            teamA.brNatprevari++;
            teamH.brNatprevari++;
            teamA.goalsDadeni+=homeGoals;
            teamH.goalsDadeni+=awayGoals;
            teamH.brLoses++;
            teamA.goalsPrimeni+=homeGoals;
            teamH.goalsPrimeni+=awayGoals;
        }
    }
    public void printTable(){
        List<Team> list1 = list.values().stream()
                .sorted(Comparator.comparing(Team::presmetajPoeni)
                .thenComparing(Team::getGoals).reversed()
                .thenComparing(Team::getName))
                .collect(Collectors.toList());
        IntStream
                .range(0,list1.size())
                .forEach(i->System.out.printf("%2d. %s\n",i+1,list1.get(i)));
    }
}
public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines()
                .map(line -> line.split(";"))
                .forEach(parts -> table.addGame(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}

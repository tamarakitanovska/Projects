package Vezbi;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

class File{
    String name;
    Integer golemina;
    LocalDateTime time;

    public File(String name, Integer golemina, LocalDateTime time) {
        this.name = name;
        this.golemina = golemina;
        this.time = time;
    }
    public String getDayAndMounth(){
        return time.getMonth() + "-" + time.getDayOfMonth();
    }

    public String getName() {
        return name;
    }

    public Integer getGolemina() {
        return golemina;
    }

    public LocalDateTime getTime() {
        return time;
    }
    public Integer getYear(){
        return getTime().getYear();
    }
}
class SetOfFiles{
    TreeSet<File> setFiles;
    public SetOfFiles(){
        setFiles = new TreeSet<>();
    }
    public void addFile(String name, int size, LocalDateTime createdAt){
        setFiles.add(new File(name,size,createdAt));
    }
}
class FileSystem{
    HashMap<Character,SetOfFiles> hashFiles;
    public FileSystem(){
        hashFiles = new HashMap<>();
    }
    public void addFile(char folder, String name, int size, LocalDateTime createdAt){
        SetOfFiles s = hashFiles.computeIfAbsent(folder,k->new SetOfFiles());
        s.addFile(name,size,createdAt);
    }
    public List<File> findAllHiddenFilesWithSizeLessThen(int size){
        return hashFiles.values().stream()
                .flatMap(x->{
                    return x.setFiles.stream();
                })
                .filter(x->{
                    return Character.compare(x.getName().charAt(0),'.')==0;
                })
                .filter(x->{
                    return x.getGolemina()<size;
                })
                .collect(Collectors.toList());
    }
    public int totalSizeOfFilesFromFolders(List<Character> folders){
        return folders.stream()
                .flatMap(x->{
                    return hashFiles.get(x).setFiles.stream();
                })
                .mapToInt(x->x.getGolemina())
                .sum();
    }
    public Map<Integer, Set<File>> byYear(){
        return hashFiles.entrySet().stream()
                .flatMap(x->{
                    return x.getValue().setFiles.stream();
                })
                .collect(Collectors.groupingBy(File::getYear,Collectors.toSet()));
    }
    public Map<String, Long> sizeByMonthAndDay(){
        return hashFiles.entrySet().stream()
                .flatMap(x->{
                    return x.getValue().setFiles.stream();
                })
                .collect(Collectors.groupingBy(File::getDayAndMounth,Collectors.summingLong(File::getGolemina)));
    }
}

public class FileSystemTest {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            fileSystem.addFile(parts[0].charAt(0), parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.of(2016, 12, 29, 0, 0, 0).minusDays(Integer.parseInt(parts[3]))
            );
        }
        int action = scanner.nextInt();
        if (action == 0) {
            scanner.nextLine();
            int size = scanner.nextInt();
            System.out.println("== Find all hidden files with size less then " + size);
            List<File> files = fileSystem.findAllHiddenFilesWithSizeLessThen(size);
            files.forEach(System.out::println);
        } else if (action == 1) {
            scanner.nextLine();
            String[] parts = scanner.nextLine().split(":");
            System.out.println("== Total size of files from folders: " + Arrays.toString(parts));
            int totalSize = fileSystem.totalSizeOfFilesFromFolders(Arrays.stream(parts)
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList()));
            System.out.println(totalSize);
        } else if (action == 2) {
            System.out.println("== Files by year");
            Map<Integer, Set<File>> byYear = fileSystem.byYear();
            byYear.keySet().stream().sorted()
                    .forEach(key -> {
                        System.out.printf("Year: %d\n", key);
                        Set<File> files = byYear.get(key);
                        files.stream()
                                .sorted()
                                .forEach(System.out::println);
                    });
        } else if (action == 3) {
            System.out.println("== Size by month and day");
            Map<String, Long> byMonthAndDay = fileSystem.sizeByMonthAndDay();
            byMonthAndDay.keySet().stream().sorted()
                    .forEach(key -> System.out.printf("%s -> %d\n", key, byMonthAndDay.get(key)));


        }
        scanner.close();
    }
}

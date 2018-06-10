package Vezbi;

import javafx.scene.shape.Arc;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

abstract class Archive{
    int id;
    Date dateArchived;

    public Archive(int id) {
        this.id = id;
    }
    public abstract int getOtvoreno();
    public abstract void setOtvoreno();

    public void setDateArchived(Date dateArchived) {
        this.dateArchived = dateArchived;
    }
    public abstract boolean KojaArhiva();
    public abstract int getMaxOpen();
    public abstract String checkArchive(Archive a, Date date);

    public Date getDateArchived() {
        return dateArchived;
    }
}
class LockedArchive extends Archive{
    Date dateToOpen;

    public LockedArchive(int id, Date dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
    }

    @Override
    public int getOtvoreno() {
        return 0;
    }

    @Override
    public void setOtvoreno() {

    }

    @Override
    public boolean KojaArhiva() {
        return true;
    }

    @Override
    public int getMaxOpen() {
        return 0;
    }

    @Override
    public String checkArchive(Archive a, Date date) {
        String s;
        if (date.before(a.getDateArchived())) {
            s="Item " + a.id + " cannot be opened before" + a.getDateArchived()+"\n";
        } else {
            s="Item " + a.id + " opened at" + date+"\n";
        }
        return s;
    }
}
class SpecialArchive extends Archive{
    int maxOpen;
    int otvoreno=0;
    public SpecialArchive(int id, int maxOpen){
        super(id);
        this.maxOpen = maxOpen;
    }
    @Override
    public boolean KojaArhiva() {
        return false;
    }

    @Override
    public int getMaxOpen() {
        return maxOpen;
    }

    @Override
    public String checkArchive(Archive a, Date date) {
        String s;
        a.setOtvoreno();
        if (a.getOtvoreno() > a.getMaxOpen()) {
            s="Item " + a.id + " cannot be opened more than " + a.getMaxOpen() + " times"+"\n";
        } else {
            s="Item " + a.id + " opened at" + date+"\n";
        }
        return s;
    }

    public int getOtvoreno(){
        return otvoreno;
    }
    public void setOtvoreno(){
        otvoreno+=1;
    }
}
class ArchiveStore{
    ArrayList<Archive> list;
    StringBuilder sb;
    boolean flag;
    ArchiveStore(){
        list = new ArrayList<>();
        flag = false;
        sb = new StringBuilder();
    }
    public void archiveItem(Archive item, Date date){
        String s;
        list.add(item);
        item.setDateArchived(date);
        s = "Item "+item.id+" archived at "+date+"\n";
        sb.append(s);
    }
    public void openItem(int id, Date date) throws NonExistingItemException {
        Archive a = null;
        for (Archive archive: list) {
            if(archive.id == id && archive.getDateArchived().equals(date)){
                a=archive;
            }
        }
        if(a!=null) {
            sb.append(a.checkArchive(a,date));
        }
        else throw new NonExistingItemException("Item with id "+id+" doesn't exist");
    }

    public  String getLog(){
        return sb.toString();
    }

}
class NonExistingItemException extends Exception{
    public NonExistingItemException(String message){
        super(message);
    }
}
public class ArchiveStoreTest {
    public static void main(String[] args) {
        Instant instant = Instant.now();
        ArchiveStore store = new ArchiveStore();
        Date date = new Date(113, 10, 7);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        int n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        int i;
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            long days = scanner.nextLong();
            Date dateToOpen = new Date(date.getTime() + (days * 24 * 60
                    * 60 * 1000));
            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
            store.archiveItem(lockedArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        n = scanner.nextInt();
        scanner.nextLine();
        scanner.nextLine();
        for (i = 0; i < n; ++i) {
            int id = scanner.nextInt();
            int maxOpen = scanner.nextInt();
            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
            store.archiveItem(specialArchive, date);
        }
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            int open = scanner.nextInt();
            try {
                store.openItem(open, date);
            } catch(NonExistingItemException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(store.getLog());
    }
}


package Vezbi;
import javax.print.DocFlavor;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

class ChatRoom implements Comparable<ChatRoom>{
    private String name;
    private TreeSet<String> korisnici = new TreeSet<>();
    public ChatRoom(String name){
        this.name=name;
    }
    public void addUser(String username){
        korisnici.add(username);
    }
    public void removeUser(String username){
        Iterator<String> iterator = korisnici.iterator();
        String a;
        while (iterator.hasNext()){
            a=iterator.next();
            if(a.equals(username)){
                korisnici.remove(a);
            }
        }
    }
    public String toString(){
        if(korisnici.isEmpty()){
            return "EMPTY";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = korisnici.iterator();
        sb.append(name);
        sb.append("\n");
        while (iterator.hasNext()){
            sb.append(iterator.next());
            sb.append("\n");
        }
        return sb.toString();
    }
    public boolean hasUser(String username){
        Iterator<String> iterator = korisnici.iterator();
        while (iterator.hasNext()){
            if(iterator.next().equals(username)){
                return true;
            }
        }
        return false;
    }
    public int numUsers(){
        return korisnici.size();
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(ChatRoom o) {
        return this.numUsers()-o.numUsers();
    }
}
class ChatSystem{
    private TreeMap<String,ChatRoom> list = new TreeMap<>();
    HashSet<String> users = new HashSet<>();
    public ChatSystem(){

    }
    public void addRoom(String roomName){
        ChatRoom room = new ChatRoom(roomName);
        list.lowerEntry(roomName);
    }
    public void removeRoom(String roomName){
        list.remove(roomName);
    }
    public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        if(list.containsKey(roomName)){
            return new ChatRoom(roomName);
        }
        else{
            throw new NoSuchRoomException(roomName);
        }
    }
    public void register(String userName){
        if(users.contains(userName)){
            return;
        }
        else if(list.isEmpty()){
            users.add(userName);
        }

    }
}
class NoSuchRoomException extends Exception{
    String roomName;
    public NoSuchRoomException(String roomName){
        super();
        this.roomName=roomName;
    }
}
public class ChatSystemTest {
}


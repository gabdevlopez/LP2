import java.util.*;

public class LinkedStack implements IStackable {
   private LinkedList list= new LinkedList();
   
    public int size () {
        return list.size();
    }

    public void push(int x ) {
        list.addFirst(x);
    }

    public int pop () {
        int x; 
        x = (int)list.removeFirst();
        return x;
    }
}
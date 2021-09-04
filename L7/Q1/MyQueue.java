package Q1;

import java.util.Arrays;
import java.util.LinkedList;

public class MyQueue<E> {
    
    private LinkedList<E> list;

    public MyQueue(E[] e) {
        list = new LinkedList<>(Arrays.asList(e));
    }
    
    public MyQueue() {
        list = new LinkedList<>();
    }
    
    public void enqueue(E e) {
        list.add(e);
    }
    
    public E dequeue() {
        return list.removeFirst();
    }

    public E peek() {
        return list.getFirst();
    }
    
    public E getElement(int i) {
        return list.get(i);
    }
    
    public int getSize() {
        return list.size();
    }
    
    public boolean contains(E e) {
        return list.contains(e);
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public String toString() {
        return list.toString();
    }
    
    public static void main(String[] args) {
        
        MyQueue<String> fruit = new MyQueue(new Object[]{"Durian", "Blueberry"});
        
        System.out.println(fruit+"\n");     // display initial items
        // Add Apple, orange, grapes, cherry
        fruit.enqueue("Apple");
        fruit.enqueue("Orange");
        fruit.enqueue("Grapes");
        fruit.enqueue("Cherry");

        System.out.println("\n"+fruit); //display items
        System.out.println("\nTop item : "+fruit.peek());
        System.out.println("Queue size : "+fruit.getSize());
        fruit.dequeue();                // delete durian (FIFO)
        
        System.out.println("Index of position 2 : " + fruit.getElement(2));
        System.out.println("Contains 'Cherry' or not? " + fruit.contains("Cherry"));
        System.out.println("Contains 'Durian' or not? " + fruit.contains("Durian"));
        System.out.println();
        System.out.println("Is the list empty? " + fruit.isEmpty());
    }
}

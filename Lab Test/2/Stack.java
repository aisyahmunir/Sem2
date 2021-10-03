package Q2;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack{
    
    private String stack_name;
    private ArrayList<Person> list;

    public Stack(String stack_name, ArrayList<Person> list) {
        this.stack_name = stack_name;
        list = new ArrayList<>();
    }
    
    // overload push method where more than one paremeter
    public void push(Person p) {        
        list.add(p);
    }
    
    public void push(String name, int age){
        Person temp = new Person(name, age);
        list.add(new Person(name, age));
    }
    
     public void push(String name, int age, String gender, String occupation) {
        Person temp = new Person(name, age, gender, occupation);
        list.add(new Person(name, age, gender, occupation));
    }
     
    // remove element inserted at peek
     public Person pop() {
        if (isEmpty())
            throw new EmptyStackException();

        Person popped = list.get(getSize() - 1);
        list.remove(popped);

        return popped;
    }
    
    // show element at top or last inserted
    public Person peek() {
        if (isEmpty())
            throw new EmptyStackException();

        return list.get(getSize() - 1);
    }
    
    // display how many element in list
    public Person get(int index){
        return list.get(index);
    }
    
    public int getSize() {
        return list.size();
    }
    
    // check wheter list has element or empty
    public boolean isEmpty() {
        return getSize() == 0;
    }
    
    // display all element
    public String toString() {
        String print = stack_name + ": ";
        for (Object e: list.toArray())
            print += e + " ";
        
        return print;
    }
    
    public void displayStack() {
        System.out.println("----" + stack_name + "----");
        if (isEmpty()) {
            System.out.println("Empty stack!");
            return;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(i + " " + list.get(i).toString());
            System.out.println();
        }
    }
    
    // sort which occupation is frontliners
    public boolean search(Person o) {
        return list.contains(o);
    }

    public Person[] toArray() {
        int n = list.size();
        Person[] out = new Person[n];
        for (int i = 0; i < n; i++) {
            out[i] = pop();
        }
        return out;
    }

    public void reverse() {
        ArrayList<Person> temp = new ArrayList<>();

        for (int i = 0; i < getSize(); i++)
            temp.add(list.get(i));

        list = temp;
    }
    
}

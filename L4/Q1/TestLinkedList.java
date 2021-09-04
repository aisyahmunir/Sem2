package Q1;

public class TestLinkedList {
    public static void main(String[] args) {

        //Initializing the list
        MyLinkedList<String> list = new MyLinkedList<>();
        // a) Append the following : a, b, c, d, e
        System.out.println("Appending a, b, c, d, e");
        list.add(0, "a");
        list.add(1, "b");
        list.add(2, "c");
        list.add(3, "d");
        list.add(4, "e");
        // b) Print all the elements in the list
        list.print();
        // c) Reverse all the elements in the list
        list.reverse();
        // d) Retrieve the number of elements in the list
        System.out.println("Size : " + list.getSize());
        // e) Retrieve the first and last value
        System.out.println("First value : " + list.getFirst());
        System.out.println("Last value : " + list.getLast());
        // f) Delete the middle value
        System.out.println("After removing the middle : ");
        list.remove(list.getSize()/2);
        list.print();
        // g) Retrieve the index location for the second and third value
        System.out.println("Index of second value : " + list.indexOf("b"));
        System.out.println("Index of third value : " + list.indexOf("c"));
        // h) Checks if the list has the value "c"
        if(list.contains("c")){
            System.out.println("Contains c");
        }
        else{
            System.out.println("Does not contains c");
        }
        // i) Replace the items individually with the following : h,e,l,l,o
        System.out.println("After replacing : ");
        list.clear();
        list.add(0, "H");
        list.add(1, "E");
        list.add(2, "L");
        list.add(3, "L");
        list.add(4, "O");
        list.print();
        System.out.println("Middle value is : " + list.getMiddleValue());
    }
}


package Q1;

public class ArrayBagDemo {
    
    public static void main(String[] args) {
    
    /* Create two (2) empty ArrayBag objects of the type array for
    string"};
    * a list of strings, named bag1, bag2 */
    BagInterface<String> bag1 = new ArrayBag<>(); 
    BagInterface<String> bag2 = new ArrayBag<>();

    /* Then filling an initially empty bag, bag1 ad bag2 with
    * a list of strings using the method testAdd
    * Display the contents of bag1 and bag2 using method displayBag */
    String[] contentsOfBag1 = {"A", "A", "B", "A", "C", "A"};
    String[] contentsOfBag2 = {"A", "B", "A", "C", "B", "C", "D", "another string"};

    System.out.println("Bag 1:"); testAdd(bag1,contentsOfBag1); 
    displayBag(bag1);
    System.out.println();
    System.out.println("Bag 2:"); testAdd(bag2,contentsOfBag2); 
    displayBag(bag2);

    /*Test the method union using a new bag named bag3
    * that combines the contents of bag1 and bag2.
    * Display the contents of bag3 using method displayBag. */
    System.out.println("Bag 3, test the method union +" + "of Bag 1 and Bag 2:");
    
    BagInterface<String> bag3 = bag1.union(bag2);
    }
    
// Tests the method add.
private static void testAdd(BagInterface<String> aBag, String[] content) {
    System.out.print("Adding ");
    
    for (int index = 0; index < content.length; index++) {
        aBag.add(content[index]); 
        System.out.print(content[index] + " ");
    } // end for System.out.println();
} // end testAdd

// Tests the method toArray while displaying the bag.
private static void displayBag(BagInterface<String> aBag) {
    System.out.println("The bag contains " + aBag.getCurrentSize() + " string(s), as follows:");

    Object[] bagArray = aBag.toArray();

    for (int index = 0; index < bagArray.length; index++) {
        System.out.print(bagArray[index] + " "); } // end for
        System.out.println(); 
    } // end displayBag
}// end ArrayBagDemo


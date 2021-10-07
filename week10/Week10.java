package week10;

public class Week10 {
    
    public static void main(String[] args) {
        
        //create object from the class
        // obj wo arguments
        // a - ABC, Kepong
        
        restaurant a = new restaurant();
        restaurant b = new restaurant();
        // a.name = "ABC"; cannot, must use mutator
        a.setName("ABC");
        //a.getAdress("A");
        a.setNumOfEmployee(20);
        b.setName("CDE");
        //b.getAdress("v");
        b.setNumOfEmployee(10);
        
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println("");
        
        if (a.getNumOfEmployee() > b.getNumOfEmployee())
            System.out.println(a.getName() + " is bigger.");
        else
            System.out.println(b.getName() + " is bigger.");
        
        
    }
}

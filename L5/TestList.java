package SList;

public class TestList {
    
    public static void main(String[] args) {
       
        SList<String> list = new SList<>();
    
        list.appendEnd("Linked ");
        list.appendEnd("list, ");
        list.appendEnd("is, ");
        list.appendEnd("easy.");
        list.display();         //display all word inserted
        System.out.println();
        
        list.removeInitial();   //remove "Linked"
        list.removeInitial();   //remove "list"
        list.display();         // display left word
        System.out.println();
        
        //check this word avaiilable or not
        System.out.println(list.contains("difficult")); 
        System.out.println("");
    
        list.clear();           //delete all items
    }
}
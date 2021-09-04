package Recursion;

public class Q1 {
    
    public static void main(String[] args) {
        System.out.println(substituteAI("flabbergasted"));
        System.out.println(substituteAI("Astronaut"));
        
    }
    
    public static String substituteAI(String str){
        if(str.length() == 1) {             // base case
            return Character.toString(str.charAt(0));
        } else {
            if (str.charAt(0)=='a')
                return Character.toString('i') + substituteAI(str.substring(1));
            return Character.toString(str.charAt(0)) + substituteAI(str.substring(1));
        }
    }
}

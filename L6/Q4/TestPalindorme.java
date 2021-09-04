package Q4;

import java.util.Scanner;

public class TestPalindorme {
    
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        MyStack<Character> num = new MyStack<>();
        
        System.out.println("Enter a string : ");
        String str = s.next();
        
        for (int i = 0; i < str.length(); i++) {
            num.push(str.charAt(i));
        }
        
        MyStack<Character> num2 = new MyStack<>();
        for (int i = num.getSize() / 2; i > 0; i--) {
            num2.push(num.pop());
        }

        if (str.length() % 2 != 0) {
            num.pop();
        }

        if (num.toString().equals(num2.toString())) {
            System.out.println();
            System.out.println("String is a palindrome");
        } else {
            System.out.println();
            System.out.println("String is not a palindrome");
        }

        
    }
}

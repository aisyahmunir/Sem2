
package Q2;

import java.util.Scanner;

public class TestIntMyStack {
    
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        MyStack<Integer> num = new MyStack<>();
        
        System.out.println("Enter an integer : ");
        int count = s.nextInt();
        
        for (int i = 1; i <= count; i++) {
            num.push(i);
        }
        
        System.out.println("Size of stack : " + num.getSize());
        System.out.println("Contents of stack : ");
        
        for (int i = num.getSize(); i > 0; i--) {
            System.out.println(num.pop());
        }
    }
}

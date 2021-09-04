
package L1Q2;

import java.io.FileInputStream;
import java.util.Scanner;

public class Lab1Q2 {

    public static void main(String[] args) {
        int count=0;
        try {
            Scanner s = new Scanner(new FileInputStream("text1.txt"));
            while(s.hasNextLine()) {
                String[] text = s.nextLine().split(",");
                for (int i = 0; i < text.length; i++) {
                    System.out.print(text[i]);
                    count++;
                }
                System.out.println("");
            }
            System.out.println("\nThe number of characters are " + count);
            System.out.println("");
        } catch (Exception e) {
            System.out.println("File was not found.");
        }
        
        try {
            Scanner s = new Scanner(new FileInputStream("text2.txt"));
            while(s.hasNextLine()) {
                String[] text = s.nextLine().split(", ");
                for (int i = 0; i < text.length; i++) {
                    System.out.print(text[i]);
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println("File was not found.");
        }
        System.out.println("");
        
        try {
            Scanner s = new Scanner(new FileInputStream("text3.txt"));
            while(s.hasNextLine()) {
                String[] text = s.nextLine().split("; ");
                for (int i = 0; i < text.length; i++) {
                    System.out.print(text[i]);
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println("File was not found.");
        }
        System.out.println("");
        
        try {
            Scanner s = new Scanner(new FileInputStream("text4.txt"));
            while(s.hasNextLine()) {
                String[] text = s.nextLine().split("123456789");
                for (int i = 0; i < text.length; i++) {
                    System.out.print(text[i]);
                }
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println("File was not found.");
        }
    }   
}


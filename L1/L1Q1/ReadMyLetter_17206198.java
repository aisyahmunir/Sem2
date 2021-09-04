package L1Q1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadMyLetter_17206198 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        
        try{
            String filename = "Aisyah_17206198.txt";
            String line;
            
            try (FileReader file = new FileReader(filename)) {
                BufferedReader bufferReader = new BufferedReader(file);
                
                while ((line = bufferReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            
            Scanner input = new Scanner(System.in);
            
            System.out.println("\n\nEnter your next letter :\n");
            
            String secLetter = input.nextLine();
                
            FileWriter outputFile = new FileWriter(filename, true);
            
            try (BufferedWriter output = new BufferedWriter(outputFile)) {
                output.write(secLetter);
            }
            
            FileReader inputFileAfterAppend = new FileReader(filename);
            
            try (BufferedReader bufferReaderAfterAppend = new BufferedReader(inputFileAfterAppend)) {
                String line2;
                
                while ((line2 = bufferReaderAfterAppend.readLine()) != null) {
                    System.out.println(line2);
                } 
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File was not found.");
        }catch(IOException e){
            System.out.println("Error reading from file");
        }
    }
}

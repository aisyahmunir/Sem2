package alwaysontimedelivery;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataFromTextFile {
    
    private String text;

    public DataFromTextFile(String text) {
        this.text = text;
    }

    public int getNumOfCustomer() {  
        int N = 0;
        try {
            Scanner in = new Scanner(new FileInputStream(text));
            N = in.nextInt();
            in.close();
        }catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return N;
    }

    public int getCapacity() {  //acc max capacity
        String[] capacity = {};
        try {
            Scanner in = new Scanner(new FileInputStream(text));
            capacity = in.nextLine().split(" ");
            in.close();
        }catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return Integer.parseInt(capacity[1]);
    }

    public int[][] getCoordinate() {  //access coordinate for every locations
        String[] data = new String[getNumOfCustomer()+1];
        try {
            Scanner in = new Scanner(new FileInputStream(text));
            int i = 0;
            while (in.hasNextLine()) {
                data[i] = in.nextLine();
                i++;
            }
            in.close();
        }catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

        int[][] location = new int[getNumOfCustomer()][2];
        for (int j = 1; j < data.length; j++) {
            String[] temp = data[j].split(" ");
            for (int k = 0; k < 2; k++) {
                int num = Integer.parseInt(temp[k]);
                location[j-1][k] = num;
            }
        }
        return location;
    }

    public int[] getDemandSize() {  
        String[] data = new String[getNumOfCustomer()+1];
        try {
            Scanner in = new Scanner(new FileInputStream(text));
            int i = 0;
            while (in.hasNextLine()) {
                data[i] = in.nextLine();
                i++;
            }
            in.close();
        }catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        int[] demand = new int[getNumOfCustomer()];
        for (int j = 1; j < data.length; j++) {
            String[] temp = data[j].split(" ");
            demand[j-1] = Integer.parseInt(temp[2]);
        }
        return demand;
    }
    
}

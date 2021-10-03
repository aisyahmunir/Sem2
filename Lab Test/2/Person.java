package Q2;

import java.util.ArrayList;

public class Person {
    
    private String name, occupation, gender;
    private int age;
    private boolean frontliners = false;    //determining occupation forntliners or not
    final private String[] occupationList = {"doctor", "nurse", "teacher", "police"};
            
    // initialise name and age
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // constructors to cater for when the input has more parameters.
    public Person(String name, int age, String gender, String occupation) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
    }
    
    // only set doctor, nurse, teacher and police as frontliners
    public void setFrontliners(boolean frontliners) {
        for (String occ : occupationList) {
            if (occ.equalsIgnoreCase(occupation)) {
                frontliners = true;
                return;
            }
        }
        frontliners = false;
    }
    
    public int getAge(){
        return age;
    }
    
    public String toString(){
        String isfront;
        if (frontliners) {
            isfront = "Frontliner";
        }else
            isfront = "Not frontliner";
        
        return name + " " + age + " " + gender + " " + isfront;
    }
    
    public boolean isFrontliner(){
        return frontliners;
    }
}


package week10;

public class restaurant {
    
    private String name, adress;
    private int numOfEmployee;
    
    public restaurant(){
        
        name = " ";
        adress = " ";
        numOfEmployee = 0;
    }

    public restaurant(String name, String adress, int numOfEmployee) {
        this.name = name;
        this.adress = adress;
        this.numOfEmployee = numOfEmployee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setNumOfEmployee(int numOfEmployee) {
        this.numOfEmployee = numOfEmployee;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public int getNumOfEmployee() {
        return numOfEmployee;
    }
    
    public String toString(){
        String str =  "Restaurant : " + name + "\nAdress : " + adress + "\n";
        str = str + "Number of Employee : " + numOfEmployee;
        return str;
    }
    
    
}

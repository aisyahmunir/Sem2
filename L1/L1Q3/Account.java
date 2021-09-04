
package lab1q3;

import java.util.Date;

public class Account {
    
    //Define varables 1 & 2
    private int id;
    private double balance;
    private  static double annualInterestRate;
    private java.util.Date dateCreated;
    
    public Account() {
        dateCreated = new java.util.Date();
    
    }
    public Account(int newId, double newBalance) {
        id = newId;
        balance = newBalance;
        dateCreated = new java.util.Date();
    }

    public int getId() {
        return this.id;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public static double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setId(int newId) {
        id = newId;
    }
    
    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    public void setAnnualInterestRate(double newAnnualInterestRate) {
        annualInterestRate = newAnnualInterestRate;
    }
    
    public double getMonthlyInterestRate() {
        return (annualInterestRate / 1200);
    }
  
    public double getMonthlyInterest() {
        return balance * getMonthlyInterestRate();
    }

    public java.util.Date getDateCreated() { 
        return dateCreated;
    }
    
    public void withdraw(double amount) {
        balance -= amount; 
    }
  
    public void deposit(double amount) {
    balance += amount;
    }
}




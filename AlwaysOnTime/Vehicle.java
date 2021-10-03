package alwaysontimedelivery;

import java.util.LinkedList;

public class Vehicle {
    
    protected int capacity;
    private double cost;
    protected LinkedList<MyCustomer> route;

    public Vehicle(int capacity) {
        this.capacity = capacity;
    }

    public Vehicle(LinkedList<MyCustomer> route, double cost, int capacity) {
        this.capacity = capacity;
        this.route = (LinkedList<MyCustomer>) route.clone();
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean allVisited(boolean[] visited) { //check semua dah dlm list ke?
        for (boolean b : visited)
            if (!b) {
                return false;
            }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < route.size()-1; i++) {
            str.append(route.get(i).CustID + " -> ");
        }
        str.append(0);
        return str + "\nCapacity: " + capacity + "\nCost: " + cost;
    }
    
}

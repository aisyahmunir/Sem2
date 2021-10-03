package alwaysontimedelivery;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TourRoute {
    
    private double tour_cost;
    private ArrayList<LinkedList<MyCustomer>> allRoute = new ArrayList<>();
    double[][] adjMatrix;
    List<MyCustomer> customer;

    public TourRoute(double tour_cost) {
        this.tour_cost = tour_cost;
    }

    public TourRoute(double[][] adjMatrix, List<MyCustomer> customer) {
        this.adjMatrix = adjMatrix;
        this.customer = customer;
        tour_cost = 0;
    }

    public void add(LinkedList<MyCustomer> cust) {
        allRoute.add(cust);
    }

    public ArrayList<LinkedList<MyCustomer>> getRoute() {
        return allRoute;
    }

    public double TotalCost() {
        tour_cost = 0;
        for (int i = 0; i < allRoute.size(); i++)
            for (int j = 0; j < allRoute.get(i).size()-1; j++) {
                int start = allRoute.get(i).get(j).CustID;
                int nextStart = allRoute.get(i).get(j+1).CustID;
                tour_cost += adjMatrix[start][nextStart];
            }
        return tour_cost;
    }

    public double computeRouteCost(LinkedList<MyCustomer> customer) {
        double routeCost = 0;
        int i;
        for (i = 0; i < customer.size()-1; i++) {
            int start = customer.get(i).CustID;
            int nextStart = customer.get(i+1).CustID;
            routeCost += adjMatrix[start][nextStart];
        }
        return routeCost;
    }

    public void addStop(MyCustomer cust) {
        allRoute.get(allRoute.size()-1).add(cust);
        tour_cost = TotalCost();
    }

    public void addNewRoute() {
        LinkedList<MyCustomer> list = new LinkedList<>();
        list.add(customer.get(0));
        allRoute.add(list);
        tour_cost = TotalCost();
    }

    public MyCustomer getLastStop() {
        int routeSize = allRoute.size()-1;
        int linkedListRouteSize = allRoute.get(routeSize).size()-1;
        return allRoute.get(routeSize).get(linkedListRouteSize);
    }

    public int computeCapacity(LinkedList<MyCustomer> cust) {
        int capacity = 0;
        for (int i = 1; i < cust.size()-1; i++)
            capacity += cust.get(i).demand;
        return capacity;
    }

    public double getTourCost() {
        return tour_cost;
    }

    public void setTourCost(double tourCost) {
        this.tour_cost = tourCost;
    }

    public int getRouteSize() {
        return allRoute.size();
    }

    public void addDepot() {
        allRoute.get(getRouteSize()-1).add(customer.get(0));
        tour_cost = TotalCost();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("MCTS Simulation\nTour Cost: " + tour_cost);
        for (int i = 0; i < allRoute.size(); i++) {
            str.append("\nVehicle " + (i+1) + "\n");
            for (int j = 0; j < allRoute.get(i).size()-1; j++) {
                str.append(allRoute.get(i).get(j).CustID + " -> ");
            }
            str.append("0\n");
            str.append("Capacity: " + computeCapacity(allRoute.get(i)) +"\nCost: " + computeRouteCost(allRoute.get(i)));
        }
        return str.toString();
    }
    
    
    
}

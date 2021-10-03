package alwaysontimedelivery;

import java.util.ArrayList;

public class GreedySearchSimulation {
    
    private static ArrayList<int[]> GRoute;  // save arrays of routes for each vehicles uses
    private static ArrayList<Double> cost;  // save cost for each vehicles uses
    private static ArrayList<Integer> capacity;  // save demand for each vehicles uses
    private double costs;

    public GreedySearchSimulation() {
        GRoute = new ArrayList<>();
        cost = new ArrayList<>();
        capacity = new ArrayList<>();
        costs = 0;
    }

    private static void addGRoute(int[] r) {
        GRoute.add(r);
    }

    public double getCost() {
        return costs;
    }

    public void setCost(double c) {
        costs = c;
    }

    public boolean allVisited(boolean[] visited) {  //check semua dah visit ke?
        for (boolean b : visited) 
            if (!b) {  //kalau takde, return false
                return false;
            }
        return true;  //dah visit, true
    }

    public int shortPath(int parent, MyCustomer<Integer, Integer> cus, boolean[] visit) { 
        double min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < cus.getSize(); i++) {
            if (i != parent)
                if (cus.getEdgeCost(parent, i) < min && !visit[i]) {
                    min = cus.getEdgeCost(parent, i);
                    index = i;
                }
        }
        return index;
    }

    public double vehicleRoute(MyCustomer<Integer, Integer> cus, Vehicle car, ArrayList<Integer> visitVertex) {  
        int totalDemand = 0;
        double totalCost = 0;
        boolean[] visited = new boolean[cus.getSize()];
        int parent = 0;
        if (!visitVertex.contains(parent))
            visitVertex.add(parent);
        visited[parent] = true;
        for (Integer vertex : visitVertex) visited[vertex] = true;
        ArrayList<Integer> route = new ArrayList<Integer>();
        route.add(parent);
        int nextVertex = -1;
        while (!allVisited(visited) && totalDemand <= car.getCapacity()) {  // find the shortest path while total demand <= capacity of vehicle
            nextVertex = shortPath(parent, cus, visited);
            visited[nextVertex] = true;
            while (totalDemand + cus.getDemand(nextVertex) > car.getCapacity()) {
                nextVertex = shortPath(parent, cus, visited);
                visited[nextVertex] = true;
            }
            if (!visitVertex.contains(nextVertex))
                visitVertex.add(nextVertex);
            route.add(nextVertex);
            totalDemand += cus.getDemand(nextVertex);
            totalCost += cus.getEdgeCost(parent, nextVertex);
            parent = nextVertex;
        }
        if (visitVertex.size() == cus.getSize() || totalDemand == car.getCapacity()) {
            totalCost += cus.getEdgeCost(nextVertex, 0);
            route.add(0);
        }
        int[] vRoute = new int[route.size()];
        for (int i = 0; i < route.size(); i++) {
            vRoute[i] = route.get(i);
        }
        capacity.add(totalDemand);
        addGRoute(vRoute);
        return totalCost;
    }

    public void FindRoute(MyCustomer<Integer, Integer> cus, Vehicle car) {  //kira semua route cost
        ArrayList<Integer> visited = new ArrayList<>();
        double totalCost = 0;
        int count = 1;
        while (visited.size() != cus.getSize()) {
            double costRoute = vehicleRoute(cus, car, visited);
            cost.add(costRoute);
            totalCost += costRoute;
            count++;
        }
        setCost(totalCost);
    }

    public void printSimulation() {  // print the simulation of greedy search
        System.out.println("Tour");
        System.out.println("Tour Cost: " + costs);
        for (int i = 0; i < GRoute.size(); i++) {
            System.out.println("Vehicle " + (i + 1));
            for (int j = 0; j < GRoute.get(i).length; j++) {
                System.out.print(GRoute.get(i)[j]);
                if (j != GRoute.get(i).length - 1)
                    System.out.print(" -> ");
            }
            System.out.println("\nCapacity: " + capacity.get(i));
            System.out.println("Cost: " + cost.get(i));
        }
    }
    
    @Override
    public String toString() {
        String str = "Greedy Simulation\nTour\n";
        str += "Tour Cost: " + costs + "\n";
        for (int i = 0; i < GRoute.size(); i++) {
            str += "Vehicle " + (i + 1) + "\n";
            for (int j = 0; j < GRoute.get(i).length; j++) {
                str += GRoute.get(i)[j];
                if (j != GRoute.get(i).length - 1)
                    str += " -> ";
            }
            str += "\nCapacity: " + capacity.get(i) + "\n";
            str += "Cost: " + cost.get(i) + "\n";
        }
        return str;
    }
    
}

package alwaysontimedelivery;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BasicSimulation {
    protected int numVehicles;
    protected double[][] matrixAdj;
    protected double distance, route, tour;
    protected DepotInfo depot;
    protected List<MyCustomer> customerList;
    protected LinkedList<MyCustomer> list = new LinkedList<>();
    protected ArrayList<Vehicle> path = new ArrayList<>();


    public BasicSimulation(List<MyCustomer> list) {
        this.customerList = list;
        depot = (DepotInfo) list.get(0);
        numVehicles = 0;
        matrixAdj = new double[list.size()][list.size()]; //jarak bet 2 nodes
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j)
                    continue;
                if (matrixAdj[i][j] != 0)
                    continue;
                distance = Math.sqrt(Math.pow(list.get(i).x - list.get(j).x, 2) + Math.pow(list.get(i).y - list.get(j).y, 2));
                matrixAdj[i][j] = distance;
                matrixAdj[j][i] = distance;
            }
        }
    }

    public void displayEdges() {
        for (int i = 0; i < matrixAdj.length; i++) {
            for (int j = 0; j < matrixAdj.length; j++) {
                System.out.print("[" + matrixAdj[i][j] + "]" + " ");
            }
            System.out.println();
        }
    }

    public double computeRouteCost(LinkedList<MyCustomer> list) {
        double routeCost = 0;

        for (int i = 0; i < list.size() - 1; i++) {
            int x = list.get(i).CustID;
            int y = list.get(i + 1).CustID;
            routeCost += matrixAdj[x][y];
        }
        return routeCost;
    }

    public int AccessUnvisitedCustomer(int a) {
        for (int i = 1; i < customerList.size(); i++) {
            MyCustomer cust = (MyCustomer) customerList.get(i);
            if (!cust.Visited && matrixAdj[a][i] > 0) {
                return customerList.get(i).CustID;
            }
        }
        return -1;
    }

    public boolean allVisited(boolean[][] visitedArray) {
        for (int i = 0; i < visitedArray.length; i++) {
            for (int j = 0; j < visitedArray[0].length; j++) {
                if (!visitedArray[i][j])
                    return false;
            }
        }
        return true;
    }

    public boolean Visited() {
        for (int i = 1; i < customerList.size(); i++) {
            if (!((MyCustomer) customerList.get(i)).Visited)
                return false;
        }
        return true;
    }

    public void FindRoute() {
        int num = customerList.size() - 1;
        List<Integer> routeComb[] = new LinkedList[num];  
        double[] cost = new double[num];      
        int[] totalSize = new int[num];

        boolean[][] visited = new boolean[num][num];
        

        for (int i = 0; i < routeComb.length; i++) {
            routeComb[i] = new LinkedList<>(); 
            routeComb[i].add(0);   
        }

        for (int i = 0; i < routeComb.length; i++) {
            routeComb[i].add(i + 1);  // + poss customer
            visited[i][i] = true; 
            cost[i] += matrixAdj[0][i + 1];  //depot ke first cust
            totalSize[i] += customerList.get(i + 1).demand; //tambah demand size cust ke total size
        }

        while (!allVisited(visited)) { 
            int lastEle;

            for (int i = 0; i < routeComb.length; i++) {
                lastEle = routeComb[i].get(routeComb[i].size() - 1);  //get last element of list. We building from behind
                int nodes = 0;
                double min = Double.POSITIVE_INFINITY;
                for (int j = 1; j < matrixAdj.length; j++) {  //check distance to adjacent node of customer i
                    if (visited[i][j - 1])  //already visited node in the list.
                        continue;
                    if (matrixAdj[lastEle][j] < min && totalSize[i] + customerList.get(j).demand <= depot.max_capacity) { //of the cost and demand size is lesser
                        min = matrixAdj[lastEle][j];  //update the route cost with cheapest cost
                        nodes = j; //update the node ID
                    }

                }
                if (nodes != 0) {  //there exists a customer demand size that still can be add to the vehicle
                    visited[i][nodes - 1] = true;
                    cost[i] += min;
                    routeComb[i].add(nodes);
                    totalSize[i] += customerList.get(nodes).demand;
                } else {  //no customer demand size can fit in the vehicle anymore, need new vehicle
                    totalSize[i] = 0;
                    cost[i] += matrixAdj[routeComb[i].get(routeComb[i].size() - 1)][0]; //go back to depot
                    routeComb[i].add(0);   //act as ending for previous route and also starting of next vehicle

                }
            }
            if (allVisited(visited)) {  //since every customer visited,return to depot
                for (int i = 0; i < routeComb.length; i++) {
                    cost[i] += matrixAdj[routeComb[i].get(routeComb[i].size() - 1)][0]; //e.g: matrixAdjacent[last node of the routeComb[0]][0]
                    routeComb[i].add(0);
                }
                break;
            }
        }

        double minCost = Double.POSITIVE_INFINITY;
        int minIndex = 0;
        for (int i = 0; i < routeComb.length; i++) {
            if (cost[i] < minCost) {
                minCost = cost[i];
                minIndex = i;
            }
        }
        tour = minCost;
        int index = 1;
        list.clear();
        int currentLoad = 0;
        list.add(customerList.get(0));
        for (int i = index; i < routeComb[minIndex].size(); i++) {  //separate the minCost path into different vehicle
            if (routeComb[minIndex].get(i) != 0) {
                list.add(customerList.get(routeComb[minIndex].get(i)));
                currentLoad += customerList.get(routeComb[minIndex].get(i)).demand;
                continue;
            }
            index = i;
            list.add(customerList.get(0));
            route = computeRouteCost(list);
            path.add(new Vehicle(list, route, currentLoad));
            list.clear();
            currentLoad = 0;
            list.add(customerList.get(0));
        }
    }

    public String displayPath(ArrayList<Vehicle> vehicleList) {
        String s = "";
        for (int i = 1; i <= vehicleList.size(); i++) {
            s += "Vehicle " + i + "\n";
            s += vehicleList.get(i - 1) + "\n";
        }
        return s;
    }

    @Override
    public String toString() {
        String s = "Basic Simulation\n";
        s += "Tour\nTour Cost: " + tour + "\n";
        s += displayPath(path);
        return s;
    }
   
    
}

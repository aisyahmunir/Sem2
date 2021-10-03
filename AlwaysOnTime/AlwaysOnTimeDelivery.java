package alwaysontimedelivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlwaysOnTimeDelivery {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the name of the file: ");
        DataFromTextFile data = new DataFromTextFile(s.nextLine());
        System.out.println();
        int[][] pos = data.getCoordinate(); // semua coordinate termasuk depot
        int[] demand = data.getDemandSize();
        MyCustomer<Integer, Integer> customer = new MyCustomer<>(); // class graph
        for (int i = 0; i < data.getNumOfCustomer(); i++) {
            customer.addCustomer(i, pos[i][0], pos[i][1], demand[i]);
        }
        
        System.out.println("Number of Customer(s): " + (customer.getSize()-1));
        System.out.println("\nCustomer and their demand");
        for (int i = 0; i < customer.getSize(); i++) {
            if (i == 0)
                System.out.println("Depot" + customer.getCoordinateCustomer(i) + ": " + customer.getDemand(i));
            else
                System.out.println("Customer " + i + customer.getCoordinateCustomer(i) + ": "  + customer.getDemand(i));
        }

        Vehicle car = new Vehicle(data.getCapacity());
        System.out.println("Capacity of each vehicles: " + car.getCapacity());
        System.out.println();
        double[][] cost = new double[customer.getSize()][customer.getSize()];
        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < cost[i].length; j++) {
                cost[i][j] = customer.computeCost(i, j);
                customer.addEdge(i, j, cost[i][j]);
            }
        }
       
        List<MyCustomer> list = new ArrayList<>();
        list.add(new DepotInfo(data.getNumOfCustomer(), data.getCapacity(), pos[0][0], pos[0][1]));
        for (int i = 1; i < pos.length; i++) {
            list.add(new MyCustomer(pos[i][0], pos[i][1], demand[i]));
        }
        //Print all simulations
        BasicSimulation bfs = new BasicSimulation(list);
        bfs.FindRoute();
        System.out.println("\n" + bfs.toString());
        
        GreedySearchSimulation greedy = new GreedySearchSimulation();
        greedy.FindRoute(customer, car);
        System.out.println(greedy.toString());
        
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch((ArrayList<MyCustomer>) list);
        mcts.search(3, 100);
        System.out.println(mcts.toString());
        
        BestFirstSearch best = new BestFirstSearch();
        best.searchRoute(data, customer, car);
        System.out.println("\n" + best.toString());
        
        
       
    }

    }
    

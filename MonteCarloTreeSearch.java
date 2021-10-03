package alwaysontimedelivery;

import java.util.ArrayList;
import java.util.Random;

public class MonteCarloTreeSearch {
    
    private double adjMatrix[][];
    private DepotInfo depot;
    private static ArrayList<MyCustomer> customer;
    private double distance;
    
    private int N;
    private double policy[][][], globalPolicy[][];
    ArrayList<MyCustomer> location;
    TourRoute best_tour = new TourRoute(Double.POSITIVE_INFINITY);
    int timeLimit = 60;
    private final int l = 3;
    private final int ite = 100;
    
    public MonteCarloTreeSearch(ArrayList<MyCustomer> customer) {
        this.customer = customer;
        depot =(DepotInfo)customer.get(0);
        N = customer.size();
        this.policy = new double[l][N][N];
        this.globalPolicy = new double[N][N];
        adjMatrix = new double[customer.size()][customer.size()]; //distance
        //forming the graph
        for (int i = 0; i < customer.size(); i++) { //edge
            for (int j = 0; j < customer.size(); j++) {
                if(i==j) continue;
                if(adjMatrix[i][j]!=0)
                    continue;
                distance = Math.sqrt(Math.pow(customer.get(i).x - customer.get(j).x,2) + Math.pow(customer.get(i).y - customer.get(j).y,2));

                adjMatrix[i][j] = adjMatrix[j][i] = distance;  // undirected edge
            }
        }
        this.location = (ArrayList<MyCustomer>) customer.clone();
    }
    
    public TourRoute search(int level, int iteration) {
        if(level==0){
            return rollout();
        }else{
            policy[level-1] = globalPolicy;
            for (int i = 0; i < iteration; i++) {
                TourRoute new_tour = search(level-1,iteration);
                if(new_tour.getTourCost() < best_tour.getTourCost()){
                    best_tour = new_tour;
                    adapt(best_tour,level);
                }
            }
            globalPolicy = policy[level-1];
        }
        return best_tour;
    }
    
    public void adapt(TourRoute a_tour, int level) {
        ArrayList<MyCustomer> visited = new ArrayList<>();
        //for every route in tour
        for (int i = 0; i < a_tour.getRouteSize(); i++) {
            for (int j = 0; j < a_tour.getRoute().get(i).size()-1; j++) {
                int ALPHA = 1;
                policy[level-1][a_tour.getRoute().get(i).get(j).CustID][a_tour.getRoute().get(i).get(j+1).CustID] += ALPHA;
                double z = 0.0;
                //for every possible move that can be made by stop
                for (int k = 0; k < location.size(); k++) {
                    if(location.get(k).CustID!=a_tour.getRoute().get(i).get(j).CustID){
                        if(!visited.contains(location.get(k))){
                            z+= Math.exp(globalPolicy[a_tour.getRoute().get(i).get(j).CustID][location.get(k).CustID]);
                        }
                    }
                }
                //for every possible move that can be made by stop
                for (int k = 0; k < location.size(); k++) {
                    if(location.get(k).CustID != a_tour.getRoute().get(i).get(j).CustID){
                        if(!visited.contains(location.get(k))){
                            policy[level - 1][a_tour.getRoute().get(i).get(j).CustID][location.get(k).CustID] -= ALPHA * (Math.exp(globalPolicy[a_tour.getRoute().get(i).get(j).CustID][location.get(k).CustID]) / z);
                        }
                    }
                }
                visited.add(a_tour.getRoute().get(i).get(j));
            }
        }
    }
    
    public TourRoute rollout() {
        MyCustomer currentStop;
        MyCustomer nextStop;

        ArrayList<MyCustomer> possible_successors = (ArrayList<MyCustomer>)location.clone();
        possible_successors.remove(0); //depot removed

        ArrayList<MyCustomer> visited = new ArrayList<>();
        ArrayList<MyCustomer> checked = new ArrayList<>();

        TourRoute new_tour = new TourRoute(adjMatrix, customer);
        new_tour.addNewRoute();

        int currentLoad = 0;

        while(true){
            currentStop = new_tour.getLastStop();
            for (int i = 0; i < possible_successors.size(); i++) {
                if(checked.contains(possible_successors.get(i)) || visited.contains(possible_successors.get(i))){
                    possible_successors.remove(i);
                }
            }
            //if no possible successor is available, return to depot
            if(possible_successors.isEmpty()){
                new_tour.addDepot(); //add depot
                //setRouteCost;
                //if all stops are visited
                if(checked.isEmpty()) {
                    //user for loop to compute Tour cost;
                    //new_tour.computeRouteCost(linkedList);
                    break; //rollout completed
                }
                //add new route into new tour
                new_tour.addNewRoute();
                currentLoad = 0;

                for (int i = 0; i < checked.size(); i++) {
                    possible_successors.add(checked.remove(i));
                }
                continue;      //skip to next loop to continue
            }
            nextStop = select_next_move(currentStop,possible_successors);

            if(currentLoad+nextStop.demand <= depot.max_capacity){
                new_tour.addStop(nextStop);
                currentLoad += nextStop.demand;
                visited.add(nextStop);
            }else{
                checked.add(nextStop);
            }
        }
        return new_tour;
    }
    
    public MyCustomer select_next_move(MyCustomer currentStop, ArrayList<MyCustomer> possible_successors) {
        double[] probability = new double[possible_successors.size()];
        double sum = 0;
        for (int i = 0; i < possible_successors.size(); i++) {
            probability[i] = Math.exp(globalPolicy[currentStop.CustID][possible_successors.get(i).CustID]);
            sum+=probability[i];
        }
        double mRand = new Random().nextDouble() * sum;
        int j = 0;
        sum = probability[0];
        while(sum<mRand){
            sum+=probability[++j];
        }
        return possible_successors.get(j);
    }
    
    public void displayTour() {
        System.out.println(search(l, ite));
    }

    @Override
    public String toString() {
        return best_tour.toString();
    }
    
}

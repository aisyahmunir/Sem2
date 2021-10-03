package alwaysontimedelivery;

public class DepotInfo extends MyCustomer{
    protected int numCustomers;
    protected int max_capacity;

    DepotInfo(int numOfCustomers, int max_capacity, int x, int y) {
        super(x, y, max_capacity);
        this.numCustomers = numCustomers;
        this.max_capacity=super.demand;
    } 
    
}

package alwaysontimedelivery;

public class MyCustomer<T extends Comparable<T>, N extends Comparable<N>> {
    
    private class Vertex<T extends Comparable<T>, N extends Comparable<N>> {
        T vertexInfo;
        int indeg, outdeg;
        private int x, y, demand;
        Vertex<T, N> nextVertex;
        Edge<T, N> firstEdge;

        public Vertex() {
            vertexInfo = null;
            indeg = outdeg = 0;
            nextVertex = null;
            firstEdge = null;
        }

        public Vertex(T v, Vertex<T, N> next, int X, int Y, int d) {
            vertexInfo = v;
            indeg = outdeg = 0;
            x = X;
            y = Y;
            demand = d;
            nextVertex = next;
            firstEdge = null;
        }
    }

    private class Edge<T extends Comparable<T>, N extends Comparable<N>> {
        Vertex<T, N> toVertex;
        double weight;
        Edge<T, N> nextEdge;

        public Edge() {
            toVertex = null;
            weight = 0;
            nextEdge = null;
        }

        public Edge(Vertex<T, N> toVertex, double weight, Edge<T, N> nextEdge) {
            this.toVertex = toVertex;
            this.weight = weight;
            this.nextEdge = nextEdge;
        }
    }

    Vertex<T, N> head;
    private int size;
    
   
    protected int x, y, demand, CustID;
    protected boolean Visited;
    protected static int code = 0;

    public MyCustomer() {
        head = null;
        size = 0;
    }
    
    // constructor for bfs
    public MyCustomer(int x, int y, int demand){
        this.x = x;
        this.y = y;
        this.demand = demand;
        Visited = false;
        this.CustID = code;
        code++;
    }

    public int getSize() {
        return size;
    }

    public boolean hasCustomer(T v) { //check customer ada ke x?
        if (head == null)
            return false;
        Vertex<T, N> temp = head;
        while (temp != null) {
            if (temp.vertexInfo.compareTo(v) == 0)
                return true;
            temp = temp.nextVertex;
        }
        return false;
    }

    public boolean addCustomer(T v, int x, int y, int demand) {
        if (hasCustomer(v) == false) {  //cust takde
            Vertex<T, N> temp = head;
            Vertex<T, N> newVertex = new Vertex<>(v, null, x, y, demand);
            if (head == null)  // Graph is empty, Point head to this vertex
                head = newVertex;
            else {
                Vertex<T, N> prev = head;
                while (temp != null) {  
                    prev = temp;
                    temp = temp.nextVertex;
                }
                prev.nextVertex = newVertex;  
            }
            size++;
            return true;
        } else
            return false;  // already added cust
    }

    public int getIndex(T v) { //cari pos customer
        Vertex<T, N> temp = head;
        int pos = 0;
        while (temp != null) {  
            if (temp.vertexInfo.compareTo(v) == 0)  //jumpa cust!
                return pos;
            temp = temp.nextVertex;  //cari vertex seterusnya sampai jumpa cust
            pos += 1;
        }
        return -1;
    }

    public boolean addEdge(T source, T destination, double w) {
        if (head == null)
            return false;
        if (!hasCustomer(source) || !hasCustomer(destination))
            return false;
        if (source == destination)
            return false;
        Vertex<T, N> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                Vertex<T, N> destinationVertex = head;
                while (destinationVertex != null) {
                    if (destinationVertex.vertexInfo.compareTo(destination) == 0) {
                        Edge<T, N> currentEdge = sourceVertex.firstEdge;
                        Edge<T, N> newEdge = new Edge<>(destinationVertex, w, currentEdge);
                        sourceVertex.firstEdge = newEdge;
                        sourceVertex.outdeg++;
                        destinationVertex.indeg++;
                        return true;
                    }
                    destinationVertex = destinationVertex.nextVertex;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return false;
    }

    public T getCustomer(int pos) {  // return customer info
        if (pos > size - 1 || pos < 0)
            return null;
        Vertex<T, N> temp = head;
        for (int i = 0; i < pos; i++)
            temp = temp.nextVertex;
        return temp.vertexInfo;
    }

    public String getCoordinateCustomer(int pos) {  
        if (pos > size - 1 || pos < 0)
            return null;
        Vertex<T, N> temp = head;
        for (int i = 0; i < pos; i++)
            temp = temp.nextVertex;
        return " (" + temp.x + ", " + temp.y + ") ";
    }

    public int getDemand(int pos) {  
        if (pos > size - 1 || pos < 0)
            return 0;
        Vertex<T, N> temp = head;
        for (int i = 0; i < pos; i++)
            temp = temp.nextVertex;
        return temp.demand;
    }
    
    public double computeCost(int source, int dest) {  //dptkan cost start dr depot
        Vertex<T, N> start = head;
        Vertex<T, N> end = head;
        for (int i = 0; i < source; i++)
            start = start.nextVertex;
        for (int i = 0; i < dest; i++)
            end = end.nextVertex;
        double sum = Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2);
        return Math.sqrt(sum);
    }

    public void printEdges() {
        Vertex<T, N> temp = head;
        while (temp != null) {
            System.out.print("# " + temp.vertexInfo + " : ");
            Edge<T, N> currentEdge = temp.firstEdge;
            while (currentEdge != null) {
                System.out.print("[" + temp.vertexInfo + ","
                        + currentEdge.toVertex.vertexInfo + "] ");
                currentEdge = currentEdge.nextEdge;
            }
            System.out.println();
            temp = temp.nextVertex;
        }
    }

    public double getEdgeCost(T source, T destination) {
        N not = null;
        if (head == null)
            return 0;
        if (!hasCustomer(source) || !hasCustomer(destination))
            return 0;
        Vertex<T, N> sourceVertex = head;
        while (sourceVertex != null) {
            if (sourceVertex.vertexInfo.compareTo(source) == 0) {
                Edge<T, N> currentEdge = sourceVertex.firstEdge;
                while (currentEdge != null) {
                    if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0)
                        return currentEdge.weight;
                    currentEdge = currentEdge.nextEdge;
                }
            }
            sourceVertex = sourceVertex.nextVertex;
        }
        return 0;
    }
    
}

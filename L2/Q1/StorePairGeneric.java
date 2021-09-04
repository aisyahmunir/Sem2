package Q1;

// public class StorePairGeneric {
public class StorePairGeneric <E extends Comparable<E>> 
implements Comparable <StorePairGeneric<E>> {
    
    private E first, second;
    
    //Constructor
    public StorePairGeneric(E first, E second) {  
    // StorePair class implement Comparable interface
        this.first = first;
        this.second = second;
    }
    
    
    public E getFirst() {
        return first;
    }
    
    public E getSecond() {
        return second;
    }

    public void setPair(E first, E second) { 
        this.first = first;
        this.second = second;
    }
    
    // Overide the Object equals() method in StorePair class
    @Override
    public boolean equals(Object obj) {
        StorePairGeneric<E> o = (StorePairGeneric<E>) obj;
        return this.first.equals(o.first);
        
        // or boolean ans = this.first(temp.first);
        // return ans;
    }
    
    // Override compareTo() method
    @Override
    public int compareTo(StorePairGeneric<E> obj){
        return this.first.compareTo(obj.first);
    }
    
    @Override
    public String toString() {
        return "first = " + first + " second = " + second;
    }
}


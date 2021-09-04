
package Q1;

public class ArrayBag<T> implements BagInterface<T> {
    
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25; 
    private int numberOfEntries;
     
    @SuppressWarnings("unchecked") 
    public ArrayBag() {
        this(DEFAULT_CAPACITY); }
        
    @SuppressWarnings("unchecked") 
    public ArrayBag(int capacity) {
        bag = (T[]) new Object[capacity]; }

    /** Gets the current number of entries in this bag.
        @return the integer number of entries currently in the bag */
        public int getCurrentSize() { 
            return numberOfEntries;
        } // end getCurrentSize
        
    /** Sees whether this bag is full.
        @return true if the bag is full, or false if not */
    public boolean isFull() {
        return (numberOfEntries == bag.length);
    } // end isFull
          
    /** Sees whether this bag is empty.
        @return true if the bag is empty, or false if not */
    public boolean isEmpty() {
        return (numberOfEntries == 0);
    }
    
    /** Adds a new entry to this bag.
        @param newEntry the object to be added as a new entry 
        @return true if the addition is successful, or false if not */ 
    public boolean add(T newEntry) {
        if (isFull())
            return false; 
        else {
            bag[numberOfEntries++] = newEntry;
            return true; }
    } // end add
        
    /** Removes one unspecified entry from this bag, if possible.
        @return either the removed entry, if the removal was successful, or null */
    public T remove() {
        T result = removeEntry(numberOfEntries - 1); 
        return result;
    } // end remove

    /** Removes one occurrence of a given entry from this bag. 
        @param anEntry the entry to be removed
        @return true if the removal was successful, or false if not */ 
    public boolean remove(T anEntry) {
    int index = getIndexOf(anEntry); 
    T result = removeEntry(index); 
    return anEntry.equals(result);
} // end remove
    
    /** Removes and return the entry at a given index within the array.
     * @param givenIndex the index of the entry to be removed
     * @ the entry if removal was successful, or null if not */
    private T removeEntry(int givenIndex){

        T result = null;

        if (!isEmpty() && (givenIndex >= 0)) { 
            result = bag[givenIndex];   // entry to remove
            numberOfEntries--;

        // replace entry to remove with last entry
        bag[givenIndex] = bag[numberOfEntries];
        bag[numberOfEntries] = null; // remove reference to last entry 
        } // end if

        return result; 
    } // end removeEntry

    /** Locates a given entry within the array bag. 
        @param anEntry the entry to be found
        @return the index of the entry, if located, or -1 otherwise*/ 
// Returns the index of the entry, if located, or -1 otherwise. 
    private int getIndexOf(T anEntry) {
        int where = -1; 
        boolean found = false;
    
        for (int index = 0; !found && (index < numberOfEntries); index++) { 
            if (anEntry.equals(bag[index])) {
                found = true;
                where = index; } // end if
        } // end for

// Assertion: If where > -1, anEntry is in the array bag, and it
// equals bag[where]; otherwise, anEntry is not in the array
        return where; 
    } // end getIndexOf

    /** Removes all entries from this bag. */

    public void clear() { 
        while(!isEmpty()) remove();
    } // end clear

    /** Counts the number of times a given entry appears in this bag. 
        @param anEntry the entry to be counted
        @return the number of times anEntry appears in the bag */
    public int getFrequencyOf(T anEntry) { 
        int counter = 0;
        for (int index = 0; index < numberOfEntries; index++) { 
            if (anEntry.equals(bag[index])) {
            counter++; 
            } // end if
        } // end for 
        return counter;
    } // end getFrequencyOf

    /** Tests whether this bag contains a given entry.
        @param anEntry the entry to locate
        @return true if this bag contains anEntry, or false otherwise */
    public boolean contains(T anEntry) { 
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) { 
            if (anEntry.equals(bag[index])) {
                found = true; 
            } // end if
        } // end for 
        return found;
    } // end contains

    /** Retrieves all entries that are in this bag.
        @return a newly allocated array of all the entries in the bag */
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; // unchecked cast
        for (int index = 0; index < numberOfEntries;index++) {
            result[index] = bag[index];
        } // end for 
        return result;
    } // end toArray

    /** Creates a new bag that combines the contents of this bag and anotherBag. @param anotherBag the bag that is to be added (Question 2)
        @return a combined bag */
    public BagInterface<T> union(BagInterface<T> anotherBag) {
        int sizeOfUnionBag = anotherBag.getCurrentSize() + getCurrentSize(); 
        BagInterface<T> unionBag = new ArrayBag<T>(sizeOfUnionBag); 
        ArrayBag<T> otherBag = (ArrayBag<T>)anotherBag;
        
        int index;
        // add entries from this bag to the new bag
        for (index = 0; index < numberOfEntries; index++) 
            unionBag.add(bag[index]);
        // add entries from the second bag to the new bag
        for (index = 0; index < otherBag.getCurrentSize(); 
                index++) unionBag.add(otherBag.bag[index]);
        
        return unionBag; 
    } // end union

    /** Creates a new bag that contains those objects that occur in both this bag and anotherBag. (Question 3)
        @param anotherBag the bag that is to be compared
        @return a combined bag */
    public BagInterface<T> intersection(BagInterface<T> anotherBag) { 
        BagInterface<T> intersectionBag = new ArrayBag<T>();
        ArrayBag<T> otherBag = (ArrayBag<T>)anotherBag; 
        BagInterface<T> copyOfAnotherBag = new ArrayBag<T>(); 
        
        int index;
    // copy the second bag
        for (index = 0; index < otherBag.numberOfEntries; index++) {
            copyOfAnotherBag.add(otherBag.bag[index]); } // end for
    // add to intersectionBag each item in this bag that matches an item in 
    // anotherBag; once matched, remove it from the second bag
    for (index = 0; index < getCurrentSize(); index++) {
        if (copyOfAnotherBag.contains(bag[index])) {
            intersectionBag.add(bag[index]);
            copyOfAnotherBag.remove(bag[index]); 
        } // end if
    } // end for
    return intersectionBag; 
    } // end intersection

    /** Creates a new bag of objects that would be left in this bag
    after removing those that also occur in anotherBag. (Question 4) 
    @param anotherBag the bag that is to be removed
    @return a combined bag */
    public BagInterface<T> difference(BagInterface<T> anotherBag) {
    BagInterface<T> differenceBag = new ArrayBag<T>(); 
    ArrayBag<T> otherBag = (ArrayBag<T>)anotherBag; 
    int index;

// copy this bag
        for (index = 0; index < numberOfEntries; index++) {
            differenceBag.add(bag[index]); } // end for

// remove the ones that are in anotherBag
        for (index = 0; index < otherBag.getCurrentSize(); index++) {
            if (differenceBag.contains(otherBag.bag[index])) {
                differenceBag.remove(otherBag.bag[index]);
            } // end if 
        } // end for
        return differenceBag; 
    } // end difference
}


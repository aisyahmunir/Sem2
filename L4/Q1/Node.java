package Q1;

public class Node<E> {  //generic class Node
    
    public E element;
    public Node<E> next;       //component of node
    
    public Node() {}    // default constructor

    public Node (E element) {       // constructor that accept item assigned to initially declared element
        this.element = element;
        this.next = null;
    } 

    public E getElement() {
        return element;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }   
}

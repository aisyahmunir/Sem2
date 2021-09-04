package SList;

public class SList<E> {
    
    private SNode<E> head, tail;
    private int size;
    

    public SList() {
        size = 0;
        head = null;
        tail = null;
    }
    
    public void appendEnd(E e) {
        if(tail == null) {
            head = tail = new SNode(e);
        }
        else {
            tail.next = new SNode(e);
            tail = tail.next;
        }
        size++;
    }
    
    public E removeInitial(){
        if (size == 0) 
            return null;
        else {
            SNode<E> temp = head;
            head = head.next;
            size--;

            return temp.element;
       }   
    }
    
    public boolean contains(E e){
        boolean result = false;
        SNode<E> current = head;
        while(current != null){
            result = current.element.equals(e);
            current = current.next;
        }
        return result;   
    }
    
    public void clear(){
        SNode<E> temp;
        
        while(head != null){
            temp = head.next;
            head.next = null;
            head = temp;
        }
        size = 0;
        System.out.println("The list is empty.");
    }
    
    public void display(){
        
        SNode<E> current = head;
        for(int i = 0; i < size; i++){
            System.out.print(current.element);
            current = current.next;
        }
    }
}

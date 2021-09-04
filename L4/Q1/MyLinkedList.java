package Q1;

public class MyLinkedList<E> {
    
    private Node<E> head, tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public void addFirst(E e){
        Node<E> firstNode = new Node<>(e);
       
        firstNode.next = head;
        head = firstNode;
        size++;
        if (size == 1)
            tail = head;
    }
    
    public void addLast(E e) {
        
        Node<E> lastNode = new Node<>(e);
        if(tail == null) {
            head = tail = lastNode;
        }
        else {
            tail.next = lastNode;
            tail = lastNode;
        }
        size++;
    }
    
    public void add(int index, E e){
        if(index == 0){
            addFirst(e);
        }
        else if(index >= size){
            addLast(e);
        }
        else{
            Node<E> current = head;
            for(int i = 0; i < index - 1; i++){
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            current.next.next = temp;
            size++;
        }
    }
    
    public E removeFirst() {
        if (size == 0) 
            return null;
        else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if(head == null) 
                tail = null;
            
            return temp.element;
       }
    }
    
    public E removeLast() {
        if (size == 0) 
            return null;
        else if (size == 1) {
            Node<E> temp = tail;
            head = tail = null;
            size--;
            return temp.element;
        }
        else{
            Node<E> temp = tail;
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }     
            tail = current;
            tail.next = null;
            size--;
            
            return temp.element;
        }
    }
    
    public E remove(int index){
        if(size == 0 || index < 0 || index >= size){
            return null;
        }
        else if(index == 0){
            return removeFirst();
        }
        else if (index == size -1){
            return removeLast();
        }
        else{
            Node<E> pre = head;
            for(int i = 0; i < index -1; i++){
                pre = pre.next;
            }
            Node<E> removed = pre.next;
            Node<E> curr = pre.next;
            pre.next = curr.next;
            size--;
            return removed.element;
        }
    }
    
     public boolean contains(E e){
        boolean result = false;
        Node<E> current = head;
        while(current != null){
            result = current.element.equals(e);
            current = current.next;
        }
        return result;
    }

    public E get(int index){
        if(index < 0 || index > size - 1){
            return null;
        }
        else{
            Node<E> current = head;
            for(int i = 0; i < index; i++){
                current = current.next;
            }
            return current.element;
        }
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public int indexOf(E e){
        Node<E> current = head;
        int index = -1;
        for(int i = 0; i < size - 1; i++){
            if(current.element.equals(e)){
                index = i;
                break;
            }
            current = current.next;
        }
        return index;
    }

    public int lastIndexOf(E e){
        Node<E> current = head;
        int index = -1;
        for(int i =0 ; i < size-1; i++){
            if(current.element.equals(e)){
                index = i;
            }
            current = current.next;
        }
        return index;
    }

    public E set(int index, E e){
        if(index < 0 || index > size-1){
            return null;
        }
        else{
            E temp = remove(index);
            add(index, e);
            return temp;
        }
    }

    public void clear(){
        while(size != 0){
            removeFirst();
        }
    }

    public void print(){
        System.out.println("The nodes include : ");
        Node<E> current = head;
        for(int i = 0; i < size; i++){
            System.out.print(current.element + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void reverse(){
        System.out.println("Reversed : ");
        int num = size;
        int index = size;
        for(int i = 0 ; i < num; i++){
            Node<E> last = head;
            for(int j = 0; j < index - 1; j++){ //loop sampai dapat last element utk round tu
                last = last.next;
            }
            System.out.print(last.element + " ");
            index--; //update last element jadi element sblm tu
        }
        System.out.println();
    }

    public E getMiddleValue(){
        return get(size/2);
    }

    public int getSize() {
        return size;
    }
    
}

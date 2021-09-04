package Q2;

public class MyGeneric <E> {
    
    private E e;
    
    public MyGeneric() {} // no arg constructor

    public MyGeneric(E e) {
        this.e = e;
    }
    // setter
    public void setE(E e) {
        this.e = e;
    }
    // getter
    public E getE() {
        return e;
    }   
}

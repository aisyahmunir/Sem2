package Q1;

public class TestGeneric {
    
    public static void main(String[] args) {
        
        StorePairGeneric a = new StorePairGeneric(6,4);
        StorePairGeneric b = new StorePairGeneric(2,2);
        StorePairGeneric c = new StorePairGeneric(6,3);
        
        System.out.println(new StorePairGeneric(6,4).compareTo(new StorePairGeneric(2,2)));
        System.out.println(new StorePairGeneric(6,4).compareTo(new StorePairGeneric(6,3)));
        System.out.println(new StorePairGeneric(2,2).compareTo(new StorePairGeneric(6,3)));
        System.out.println();
        
        System.out.println(new StorePairGeneric(6,4).equals(new StorePairGeneric(2,2)));
        System.out.println(new StorePairGeneric(6,4).equals(new StorePairGeneric(6,3)));
        System.out.println(new StorePairGeneric(2,2).equals(new StorePairGeneric(6,3)));
    }
}

package Q2;

public class TestMyGeneric {
    
    public static void main(String[] args) {
        
        MyGeneric<String> strObj = new MyGeneric<>();
        MyGeneric<Integer> intObj = new MyGeneric<>();
        
        strObj.setE("Hi");
        intObj.setE(200);
        

	System.out.println("String :" + strObj.getClass());
	System.out.println("Integer : " + strObj.getClass());

    }
}

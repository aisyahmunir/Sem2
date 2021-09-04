package Q3;

public class CompareMax {
   
    public static void main(String[] args) {
       
        System.out.printf( "Maximum number of %d, %d and %d is %d \n", 8, 1, 3, 
                maximum( 8, 1, 3) );
        
        System.out.printf( "Maximum number of %.1f, %.1f and %.1f is %.1f \n",
                4.5, 3.2, 5.6, maximum( 4.5, 3.2, 5.6) ); 
        
        System.out.printf("Maximum String of %s, %s and %s is %s \n",
                "java", "python", "Ruby", maximum( "java", "python", "Ruby"));
    }
    
    public static <T extends Comparable<T>> T maximum(T x, T y, T z){
        
        T max = x;
        if (y.compareTo(max)> 0) {
            max = y;
        }
        if (z.compareTo(max)> 0) {
            max = z;
        }
        
        return max;
        
    }
}

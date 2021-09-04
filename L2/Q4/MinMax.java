package Q4;

public class MinMax {
    
    public static void main(String[] args) {
        
        Integer[] intArr = {5, 3, 7, 1, 4, 9, 8, 2};
        String[] strArr = {"red", "blue", "orange", "tan"};
        
        String intStr = minmax(intArr);    //intStr = Min = 1, Max = 9
        String str = minmax(strArr);       // str = Min = blue, Max = tan
        
        System.out.println(intStr);
        System.out.println();
        System.out.println(str);
    }
    
    public static <E extends Comparable<E>> String minmax(E[] list) {
        
        E min = list[0];
        E max = list[0];
        
        for (int i = 0; i < list.length; i++) {
            
            if (min.compareTo(list[i]) > 0) {
                min = list[i];
            }
            if (max.compareTo(list[i]) < 0) {
                max = list[i];
            }
        }
        return "Mininum : " + min + "\nMaximum : " + max;
    }
}

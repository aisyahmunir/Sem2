package Q5;

public class FindMax {
    
    public static void main(String[] args) {
        
        Integer[] number = {1, 2, 3};
        System.out.println(Circle.max(number));
        
        String[] colour = {"red", "green", "blue"};
        System.out.println(Circle.max(colour));
        
        Circle[] circles  = {new Circle(3), new Circle(2.9), new Circle(5.9)};
        System.out.println(Circle.max(circles));
    }
    
    public static class Circle implements Comparable<Circle> {
        double radius;
        
        public Circle(double radius){
            this.radius = radius;
        }

        @Override
        public int compareTo(Circle c) {
            if (radius < c.radius) {
                return -1; }
            else if (radius == c.radius) {
                return 0; }
            else {
                return 1; }
        }

        @Override
        public String toString() {
            return "Radius : " + Double.toString(radius);
         }
    
        public static <E extends Comparable<E>> E max (E[] list) {
            E max = list[0];
        
            for (int i = 1; i < list.length; i++) {
                if (max.compareTo(list[i]) < 0) {
                    max = list[i];
                }
            }
            return max;
        }
    }  
    
}

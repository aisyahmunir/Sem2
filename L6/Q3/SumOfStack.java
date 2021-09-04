package Q3;

public class SumOfStack {
    
    public static void main(String[] args) {
        
        MyStack<Integer> S = new MyStack<>();
        
        for (int i = 1; i <= 100; i++) {
            S.push(i);
        }
        
        int sum = 0;
        for (int i = S.getSize(); i > 10; i--) {
            sum += S.pop();
        }
        System.out.println(sum);
    }
}

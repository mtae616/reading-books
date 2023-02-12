import java.util.Stack;

public class ch05_stack {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();

        s.push(5);
        s.push(2);
        s.push(3);
        s.push(7);
        s.pop();
        s.push(1);
        s.push(4);
        s.pop();
        while (!s.empty()) {
            System.out.println(s.peek());
            s.pop();
        }
    }
}
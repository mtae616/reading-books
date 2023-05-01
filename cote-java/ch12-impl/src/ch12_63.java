import java.util.Stack;

public class ch12_63 {
    static class Solution {
        public static String solution(String number, int k) {
            StringBuilder answer = new StringBuilder("");
            Stack<Integer> stack = new Stack<>();
            int[] temp = new int[number.length()];
            for (int i = 0; i < number.length(); i++) temp[i] = number.charAt(i) - '0';

            int sum = 0;
            int i = 0;
            while (i < number.length()) {
                while (!stack.isEmpty() && stack.peek() < temp[i] && sum != k) {
                    stack.pop();
                    sum += 1;
                }
                stack.push(temp[i]);
                i += 1;
            }

            while (sum < k) {
                stack.pop();
                sum += 1;
            }

            while(!stack.isEmpty()) answer.append(stack.pop());
            return answer.reverse().toString();
        }
    }

    public static void main(String[] args) {
        Solution.solution("4321", 1);
    }
}

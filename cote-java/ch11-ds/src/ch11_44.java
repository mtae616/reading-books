import java.util.Stack;

public class ch11_44 {
    static class Solution {
        public static int[] solution(int[] prices) {
            int[] answer = new int[prices.length];

            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < prices.length; i++) {
                while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                    Integer idx = stack.pop();
                    answer[idx] = i - idx;
                }
                stack.push(i);
            }

            while (!stack.isEmpty()) {
                Integer idx = stack.pop();
                answer[idx] = prices.length - idx - 1;
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[]{1, 2, 3, 2, 3});
    }
}

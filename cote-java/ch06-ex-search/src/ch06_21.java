import java.util.*;

public class ch06_21 {

    static class Solution {
        static int[] visited;
        static Stack<Character> arr = new Stack<>();
        static Set<Integer> answer = new HashSet<>();

        public static void check() {
            String temp = "";
            for (int i = 0; i < arr.size(); i++) temp += arr.get(i);
            boolean flag = true;
            int now = Integer.parseInt(temp);
            if (now < 2) return;
            for (int i = 2; i * i <= now; i++) if (now % i == 0) flag = false;
            if (flag) answer.add(now);
        }

        public static void dfs(char[] number, int iter) {
            if (iter != 0) check();
            for (int i = 0; i < number.length; i++) {
                if (visited[i] == 0) {
                    visited[i] = 1;
                    arr.push(number[i]);
                    dfs(number, iter + 1);
                    arr.pop();
                    visited[i] = 0;
                }
            }
        }

        public static int solution(String numbers) {
            visited = new int[numbers.length()];

            char[] chars = numbers.toCharArray();
            dfs(chars, 0);

            return answer.size();
        }
    }

    public static void main(String[] args) {
        Solution.solution("17");
    }
}

package ch12_58;

import java.util.Arrays;

public class ch12_58_1 {
    static class Solution {

        public static int[] visited;
        public static int answer;

        public static void dfs(int[] numbers, int iter, int idx, int target) {
            if (iter == numbers.length) {
                answer += check(numbers, target);
                return;
            }

            answer += check(numbers, target);
            for (int i = idx; i < numbers.length; i++) {
                if (visited[i] == 0) {
                    visited[i] = 1;
                    dfs(numbers, iter + 1, i, target);
                    visited[i] = 0;
                }
            }
        }

        private static int check(int[] numbers, int target) {
            int[] ints = Arrays.copyOf(numbers, numbers.length);
            int sum = 0;
            for (int i = 0; i < visited.length; i++) {
                if (visited[i] == 1) ints[i] *= -1;
                sum += ints[i];
            }
            return sum == target ? 1 : 0;
        }

        public static int solution(int[] numbers, int target) {
            visited = new int[numbers.length];
            dfs(numbers, 0, 0, target);
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[]{4, 1, 2, 1}, 4);
    }
}

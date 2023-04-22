package ch05_17;

import java.util.Objects;

public class ch05_17 {
    // A E I O U

    static class Solution {
        public static char[] alpha = {'A', 'E', 'I', 'O', 'U'};
        public static String[] arr = new String[20000];
        public static int idx = 0;

        public static void dfs(int iter, String word) {
            if (iter == 5) {
                arr[idx++] = word;
                return;
            }
            if (!word.equals("")) { arr[idx++] = word; }

            for (int i = 0; i < 5; i++) {
                dfs(iter + 1, word + alpha[i]);
            }
        }

        public static int solution(String word) {
            int answer = 0;
            dfs(0, "");
            for (int i = 0; i < arr.length; i++) {
                if (word.equals(arr[i])) answer = i;
            }

            return answer + 1;
        }
    }

    public static void main(String[] args) {
        Solution.solution("IO");
    }
}

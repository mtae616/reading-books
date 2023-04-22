package ch05_17;

import java.util.Arrays;

public class ch05_17_2 {
    static class Solution {
        public static char[] alpha = new char[]{'A', 'E', 'I', 'O', 'U'};
        public static String[] dictionary = new String[20000];
        static int idx;

        public static void dfs(String word) {
            if (word.length() > 5) return;
            if (!word.equals("")) dictionary[idx++] = word;

            for (int i = 0; i < alpha.length; i++) dfs(word + alpha[i]);
        }

        public static int solution(String word) {
            dfs("");
            return Arrays.asList(dictionary).indexOf(word) + 1;
        }
    }


    public static void main(String[] args) {
        Solution.solution("EIO");
   }
}

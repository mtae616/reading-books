import java.util.Arrays;

public class ch07_26 {

    static class Solution {
        public static String solution(String s) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new StringBuilder(String.valueOf(chars))
                    .reverse().toString();
        }
    }

    public static void main(String[] args) {
        Solution.solution("Zbcdefg");
    }
}

import java.util.Arrays;

public class ch07_27 {
    static class Solution {
        public static String[] solution(String[] strings, int n) {
            Arrays.sort(strings, (a, b) -> {
                if (a.charAt(n) != b.charAt(n)) return a.charAt(n) - b.charAt(n);
                return a.compareTo(b);
            });
            return strings;
        }
    }

    public static void main(String[] args) {
        Solution.solution(new String[]{
                "abce", "abcd", "cdx"
        }, 2);
    }
}

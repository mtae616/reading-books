import java.util.Arrays;

public class ch04_11 {
    static class Solution {
        static boolean solution(String s) {
            char[] chars = s.toCharArray();

            int sum = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'p' || chars[i] == 'P') {
                    sum += 1;
                } else if (chars[i] == 'y' || chars[i] == 'Y') {
                    sum -= 1;
                }
            }
            return sum == 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution.solution("ssdasd"));
    }
}

import java.util.Arrays;

public class ch09_36 {

    static class Solution {
        public static int solution(int[] numbers) {
            return 45 - Arrays.stream(numbers).sum();
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[]{1, 2, 3, 4, 6, 7, 8, 0});
    }
}

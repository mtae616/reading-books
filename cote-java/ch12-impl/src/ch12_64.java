import java.util.Arrays;
import java.util.Comparator;

public class ch12_64 {

    static class Solution {
        public static int solution(int[][] routes) {
            int answer = 0;
            Arrays.sort(routes, Comparator.comparingInt(route -> route[1]));

            int last = Integer.MIN_VALUE;

            for (int[] route : routes) {
                if (route[0] <= last && last <= route[1]) continue;
                last = route[1];
                answer += 1;
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[][]{
                {-20, -15},
                {-14, -5},
                {-18, -13},
                {-5, -3}
        });
    }
}

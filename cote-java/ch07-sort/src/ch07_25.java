import java.util.Arrays;

public class ch07_25 {
    static class Solution {
        public static int solution(int[] citations) {
            int answer = 0;

            Arrays.sort(citations);
            for (int h = citations.length; h >= 1; h--) {
                if (citations[citations.length - h] >= h) return h;
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[]{3, 0, 6, 1, 5});
    }
}


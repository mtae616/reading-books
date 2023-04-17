package ch08_31;

public class ch08_31_2 {

    static class Solution {
        static public long solution(int n, int[] times) {
            long start = 1L;
            long end = 1_000_000_000_000_000_000L;

            while (start < end) {
                long mid = (start + end) / 2;

                long sum = 0;
                for (int i = 0; i < times.length; i++) {
                    sum += mid / times[i];
                }
                if (sum >= n) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }

            return start;
        }
    }


    public static void main(String[] args) {
        Solution.solution(6, new int[]{7, 10});
    }
}

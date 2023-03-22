public class ch08_31 {
    static class Solution {

        public static boolean isValid(long t, int[] times, int n) {
            long result = 0;
            for (int time : times) {
                result += t / time;
            }
            return result >= n;
        }
        public static long solution(int n, int[] times) {
            long start = 1L;
            long end = 1_000_000_000_000_000_000L;

            while (start < end) {
                long mid = (start + end) / 2;

                if (isValid(mid, times, n)) end = mid;
                else start = mid + 1;
            }
            return start;
        }
    }

    public static void main(String[] args) {
        Solution.solution(6, new int[]{7, 10});
    }
}

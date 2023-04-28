import java.util.Arrays;
import java.util.stream.Collectors;

public class ch12_62 {
    static class Solution {
        public static int solution(int n, int[] lost, int[] reserve) {
            int[] alreadyHave = new int[n];

            Arrays.fill(alreadyHave, 1);
            for (int i = 0; i < lost.length; i++) alreadyHave[lost[i] - 1] -= 1;
            for (int i = 0; i < reserve.length; i++) alreadyHave[reserve[i] - 1] += 1;

            for (int i = 0; i < alreadyHave.length; i++) {
                if (alreadyHave[i] == 0) {
                    if (i > 0 && alreadyHave[i - 1] > 1) {
                        alreadyHave[i - 1] -= 1;
                        alreadyHave[i] += 1;
                    }
                    else if (i + 1 < n && alreadyHave[i + 1] > 1) {
                        alreadyHave[i + 1] -= 1;
                        alreadyHave[i] += 1;
                    }
                }
            }
            return Arrays.stream(alreadyHave).filter(e -> e > 0).toArray().length;
        }
    }

    public static void main(String[] args) {
        Solution.solution(3, new int[]{3}, new int[]{1});
    }
}

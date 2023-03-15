import java.util.Arrays;

class Solution {
    public static int[] solution(int[] array, int[][] commands) {
        return Arrays.stream(commands).mapToInt(e -> {
            int[] ints = Arrays.copyOfRange(array, (e[0] - 1), e[1]);
            Arrays.sort(ints);
            return ints[e[2] - 1];
        }).toArray();
    }
}

public class ch07_23 {
    public static void main(String[] args) {
        Solution.solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{
                {2, 5, 3},
                {4, 4, 1},
                {1, 7, 3}
        });
    }
}

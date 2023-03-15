import java.util.stream.IntStream;

class Solution {
    private static final int[][] RULES = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
    };

    private static int getPicked(int person, int problem) {
        int[] rule = RULES[person];
        int index = problem % rule.length;
        return rule[index];
    }

    public static int[] solution(int[] answers) {
        int[] corrects = new int[3];
        int max = 0;

        for (int i = 0; i < answers.length; i++) {
            int answer = answers[i];
            for (int j = 0; j < 3; j++) {
                int pricked = getPicked(j, i);
                if (answer == pricked) {
                    if (++corrects[j] > max) max = corrects[j];
                }
            }
        }

        final int maxCorrects = max;
        return IntStream.range(0, 3)
                .filter(i -> corrects[i] == maxCorrects)
                .map(i -> i + 1)
                .toArray();
    }
}

public class ch06_18 {
    public static void main(String[] args) {
//        Solution.solution(new int[]{1, 2, 3, 4, 5});
        Solution.solution(new int[]{1, 3, 2, 4, 2});
    }
}

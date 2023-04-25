package ch06_18;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ch06_18_2 {
    static class Solution {

        static int picked[][] = new int[][]{
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        public static int[] solution(int[] answers) {
            int[] answer = new int[3];

            int max = 0;
            for (int i = 0; i < 3; i++) {
                int sum = 0;
                for (int j = 0; j < answers.length; j++) {
                    if (picked[i][j % picked[i].length] == answers[j]) {
                        sum += 1;
                    }
                }
                answer[i] = sum;
                max = Math.max(max, sum);
            }

            final int finalMax = max;
            return IntStream
                        .range(0, 3)
                        .filter(i -> answer[i] == finalMax)
                        .map(i -> i + 1)
                        .toArray();
        }
    }


    public static void main(String[] args) {
        Solution.solution(new int[] {1, 3, 2, 4, 2});
    }
}

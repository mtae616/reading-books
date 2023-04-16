package ch05_16;

import java.util.ArrayList;
import java.util.List;

public class ch05_16_2 {
    static class Solution {
        static List<int[]> temp = new ArrayList<>();
        public static void hanoi(int n, int from, int to) {
            if (n == 1) {
                temp.add(new int[]{from, to});
                return ;
            }
            int empty = 6 - from - to;
            hanoi(n - 1, from, empty);
            hanoi(1, from, to);
            hanoi(n - 1, empty, to);
        }

        public static int[][] solution(int n) {
            hanoi(n, 1, 3);
            int[][] answer = new int[temp.size()][];
            for (int i = 0; i < temp.size(); i++) answer[i] = temp.get(i);
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution(2);
    }
}

import java.util.*;

public class ch12_59 {

    static class Solution {
        public static int[] parent;

        public static int findParent(int x) {
            if (x != parent[x]) parent[x] = findParent(parent[x]);
            return parent[x];
        }

        public static void unionParent(int a, int b) {
            a = findParent(a);
            b = findParent(b);
            if (b > a) {
                int replace = parent[b];
                for (int i = 0; i < parent.length; i++) {
                    if (parent[i] == replace) {
                        parent[i] = a;
                    }
                }

            }
            else {
                int replace = parent[a];
                for (int i = 0; i < parent.length; i++) {
                    if (parent[i] == replace) {
                        parent[i] = b;
                    }
                }
            }
        }

        public static int solution(int n, int[][] computers) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (computers[i][j] == 1) {
                        unionParent(i, j);
                    }
                }
            }

            Set<Integer> answer = new HashSet<>();
            for (int i = 0; i < parent.length; i++) answer.add(parent[i]);
            return answer.size();
        }
    }

    public static void main(String[] args) {
        Solution.solution(7, new int[][]{
                {1, 0, 0, 0, 0, 0, 1},
                {0, 1, 1, 0, 1, 0, 0},
                {0, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 1, 1},
        });
    }
}

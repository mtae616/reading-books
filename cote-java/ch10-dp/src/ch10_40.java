import java.util.Arrays;

public class ch10_40 {
    static class Solution {
        static final int[][] mem = new int[101][101];

        static int answer = 0;
        public static int dfs(int[][] map, int iter, int i, int j, int n, int m) {
            if (i > n || j > m) return 0;
            if (map[i][j] == -1) return 0;

            if (mem[i][j] != -1) return mem[i][j];
            if (i == n && j == m) return 1;

            int total = dfs(map, iter + 1, i + 1, j, n, m) + dfs(map, iter + 1, i, j + 1, n, m);
            return mem[i][j] = total % 1000000007;
        }
        public static int solution(int m, int n, int[][] puddles) {
            int map[][] = new int[n + 1][m + 1];
            for (int[] row : mem) Arrays.fill(row, -1);

            for (int i = 0; i < puddles.length; i++) map[puddles[i][1]][puddles[i][0]] = -1;
            int total = dfs(map, 0, 1, 1, n, m);
            System.out.println(total);

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    System.out.print(mem[i][j] + " ");
                }
                System.out.println();
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Solution.solution(4, 3, new int[][]{{2, 2}});
    }
}

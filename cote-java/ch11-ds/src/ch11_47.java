import java.util.Arrays;

public class ch11_47 {
    static class Solution {

        public static int INF = (int) 1e9;
        public static int solution(int n, int[][] results) {
            int answer = 0;
            int[][] graph = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) Arrays.fill(graph[i], INF);

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) graph[i][j] = 0;
                }
            }

            for (int i = 0; i < results.length; i++) graph[results[i][0]][results[i][1]] = 1;

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                int sum = 0;
                for (int j = 1; j <= n; j++) {
                    if (graph[i][j] != INF || graph[j][i] != INF) sum += 1;
                }
                if (sum == n) answer += 1;
            }
            return answer;
        }
    }
}

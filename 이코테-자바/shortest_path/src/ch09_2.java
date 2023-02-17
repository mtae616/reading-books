import java.util.Arrays;
import java.util.Scanner;

public class ch09_2 {

    public static int INF = (int) 1e9;
    public static int[][] graph = new int[101][101];
    public static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < graph.length; i++) Arrays.fill(graph[i], INF);

        n = sc.nextInt(); // 회사
        m = sc.nextInt(); // 경로
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) graph[i][j] = 0;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        int x = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(graph[1][k] + graph[x][k]);

    }
}

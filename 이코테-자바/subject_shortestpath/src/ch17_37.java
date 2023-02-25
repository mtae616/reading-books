import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ch17_37 {

    public static int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        int[][] arr = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(arr[i], INF);

        for (int i = 0; i < m; i++) {
            String[] s = bf.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            int val = Integer.parseInt(s[2]);
            if (val < arr[a][b]) arr[a][b] = val;
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == j) arr[i][j] = 0;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] == INF) System.out.print(0 + " ");
                else System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        bf.close();
    }
}
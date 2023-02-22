import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ch16_31 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(bf.readLine());
        for (int o = 0; o < tc; o++) {
            String[] s = bf.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);

            int[][] arr = new int[n][m];
            String[] arrList = bf.readLine().split(" ");
            int idx = 0;
            for (int l = 0; l < n; l++) {
                for (int k = 0; k < m; k++) {
                    arr[l][k] = Integer.parseInt(arrList[idx++]);
                }
            }

            for (int j = 1; j < m; j++) {
                for (int i = 0; i < n; i++) {
                    int left = 0;
                    int left_up = 0;
                    int left_down = 0;

                    left = arr[i][j - 1];
                    if (i - 1 >= 0) left_up = arr[i - 1][j - 1];
                    if (i + 1 < n) left_down = arr[i + 1][j - 1];
                    arr[i][j] += Math.max(Math.max(left, left_up), left_down);
                }
            }

            int result = 0;
            for (int i = 0; i < n; i++) {
                result = Math.max(result, arr[i][m - 1]);
            }
            System.out.println(result);

        }
        bf.close();
    }
}
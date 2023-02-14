import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ch08_5 {
    public static int[] dp = new int[10001];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] s = bf.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        int arr[] = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(bf.readLine());
        for (int i = 0; i < 10001; i++) dp[i] = 99999;

        dp[0] = 0;
        for (int c : arr) {
            for (int i = c; i < 10001; i++) {
                dp[i] = Math.min(dp[i], dp[i - c] + 1);
            }
        }

        System.out.println(dp[m]);
        bf.close();
    }
}

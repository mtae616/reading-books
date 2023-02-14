import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ch08_4 {
    public static int[] dp = new int[1001];
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i < n + 1; i++) dp[i] = (dp[i - 1] + 2 * dp[i - 2]);
        System.out.println(dp[n]);

        bf.close();
    }
}

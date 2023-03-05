import java.io.*;
import java.util.StringTokenizer;

public class ch16_33 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] days = new int[n];
        int[] costs = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            days[i] = Integer.parseInt(st.nextToken());
            costs[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[16];
        for (int i = n - 1; i >= 0; i--) {
            if (i + days[i] <= n) dp[i] = Math.max(costs[i] + dp[days[i] + i], dp[i + 1]);
            else dp[i] = dp[i + 1];
        }

        System.out.println(dp[0]);
        bw.flush();
        bw.close();
        br.close();
    }
}

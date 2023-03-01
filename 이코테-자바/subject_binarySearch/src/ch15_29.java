import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ch15_29 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] houses = new int[n];
        for (int i = 0; i < n; i++) houses[i] = Integer.parseInt(br.readLine());
        Arrays.sort(houses);

        int result = 0;
        int left = 1;
        int right = houses[n - 1] - houses[0];
        while (left <= right) {
            int mid = (left + right) / 2;
            int value = houses[0];
            int cnt = 1;
            for (int i = 1; i < n; i++) {
                if (houses[i] >= value + mid) {
                    value = houses[i];
                    cnt += 1;
                }
            }
            if (cnt >= c) {
                left = mid + 1;
                result = mid;
            }
            else right = mid - 1;
        }

        bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
        br.close();
    }
}

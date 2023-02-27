import java.io.*;
import java.util.Arrays;

public class ch15_28 {

    public static int[] arr = new int[1000001];
    public static int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        Arrays.fill(arr, INF);
        for (int i = 0; i < s.length; i++) { arr[i] = Integer.parseInt(s[i]); }

        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;

        boolean flag = false;
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == mid) {
                flag = true;
                result = mid;
                break;
            }
            if (arr[mid] > mid) right = mid - 1;
            else left = mid + 1;
        }

        if (flag) bw.write(String.valueOf(result));
        else bw.write(String.valueOf(-1));

        bw.flush();
        bw.close();
        br.close();
    }
}

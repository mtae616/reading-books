import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ch07_3 {

    public static void binSearch(int[] arr, int n, int target) {
        int start = 0;
        int end = arr[n - 1];

        int result = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            System.out.println(mid);
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int temp = arr[i] - mid;
                if (temp < 0) continue;
                sum += temp;
            }
            if (sum < target) end = mid - 1;
            else {
                result = mid;
                start = mid + 1;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] s = bf.readLine().split(" ");
        String[] temp = bf.readLine().split(" ");
        bf.close();

        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]); // 손님이 요청한 길이

        int[] heights = new int[n];
        for (int i = 0; i < n; i++) heights[i] = Integer.parseInt(temp[i]);
        Arrays.sort(heights);

        binSearch(heights, n, m);
    }
}

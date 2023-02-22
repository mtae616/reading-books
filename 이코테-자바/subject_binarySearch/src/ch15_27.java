import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ch15_27 {
    public static int lowerBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= target) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    public static int upperBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] > target) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    public static int countByRange(int[] arr, int left, int right) {
        int rightIndex = upperBound(arr, right, 0, arr.length);
        int leftIndex = lowerBound(arr, left, 0, arr.length);
        return rightIndex - leftIndex;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bf.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int x = Integer.parseInt(s[1]);

        String[] s1 = bf.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < s1.length; i++) arr[i] = Integer.parseInt(s1[i]);

        int cnt = countByRange(arr, x, x);

        if (cnt == 0) System.out.println(-1);
        else System.out.println(cnt);
        bf.close();
    }
}
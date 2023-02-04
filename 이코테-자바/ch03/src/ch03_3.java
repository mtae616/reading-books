import java.util.Arrays;
import java.util.Scanner;

public class ch03_3 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//
//        int[][] arr = new int[n][m];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                arr[i][j] = sc.nextInt();
//            }
//        }
//
//        int maxValue = 0;
//        for (int i = 0; i < n; i++){
//            int min = Arrays.stream(arr[i]).min().getAsInt();
//            maxValue = Math.max(min, maxValue);
//        }
//        System.out.println(maxValue);

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int result = 0;

        for (int i = 0; i < n; i++) {
            int min_value = 2147483647;
            for (int j = 0; j < m; j++) {
                int x = sc.nextInt();
                min_value = Math.min(min_value, x);
            }
            result = Math.max(result, min_value);
        }
        System.out.println(result);
    }
}

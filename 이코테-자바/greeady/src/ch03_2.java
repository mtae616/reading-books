import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ch03_2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr, Collections.reverseOrder());
        int sum = 0;
        int tempK = k;
        while (m > 0) {
            if (tempK > 0) {
                sum += arr[0];
                tempK -= 1;
            } else {
                sum += arr[1];
                tempK = k;
            }
            m -= 1;
        }
        System.out.println(sum);


//        int N = 5; // 배열의 크기
//        int M = 8; // 숫자가 더해지는 횟수
//        int K = 3; // k 번 초과할 수는 없음
//        Integer[] temp = {2, 4, 5, 4, 6};
//        Arrays.sort(temp, Collections.reverseOrder());
//
//        int sum = 0;
//        while (M > 0) {
//            if (K > 0) {
//                sum += temp[0];
//                K -= 1;
//            } else {
//                sum += temp[1];
//                K = 3;
//            }
//            M -= 1;
//        }
//        System.out.println(sum);
    }
}

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ch06_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();

        int[] a = new int[n];
        Integer[] b = new Integer[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.nextLine();
        for (int j = 0; j < n; j++) {
            b[j] = sc.nextInt();
        }

        sc.nextLine();

        Arrays.sort(a);
        Arrays.sort(b, Collections.reverseOrder());

        for (int i = 0; i < k; i++) {
            if (a[i] < b[i]) {
                int temp = a[i];
                a[i] = b[i];
                b[i] = temp;
            }
        }

        System.out.println(Arrays.stream(a).sum());
    }
}

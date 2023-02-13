import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ch08_3 {

    public static int[] foods = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        String[] s = bf.readLine().split(" ");
        int[] arr = new int[n];
        for(int i = 0; i < s.length; i++) arr[i] = Integer.parseInt(s[i]);
        foods[0] = arr[0];
        foods[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < arr.length; i++) {
            foods[i] = Math.max(foods[i - 1], foods[i - 2] + arr[i]);
        }
        System.out.println(foods[n - 1]);
        bf.close();
    }
}

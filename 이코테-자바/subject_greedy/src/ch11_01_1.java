import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class ch11_01_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        Integer[] arr = new Integer[n];

        String[] s = bf.readLine().split(" ");
        for (int i = 0; i < s.length; i++) { arr[i] = Integer.parseInt(s[i]); }

        Arrays.sort(arr, Collections.reverseOrder());

        int cnt = 0;
        int i = 0;
        while (i < arr.length) {
            int buf = arr[i];
            while (buf > 0) {
                buf -= 1;
                i += 1;
            }
            cnt += 1;
        }

        System.out.println(cnt);
        bf.close();
    }
}

//3 2 2 2 1
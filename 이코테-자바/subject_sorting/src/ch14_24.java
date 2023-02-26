import java.io.*;
import java.util.Arrays;

public class ch14_24 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) { arr[i] = Integer.parseInt(input[i]); }
        Arrays.sort(arr);

        bw.write(String.valueOf(arr[(n - 1) / 2]));

        bw.flush();
        br.close();
        bw.close();
    }
}

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ch11_04 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int countToken = st.countTokens();
        for (int i = 0; i < countToken; i++) { arr[i] = Integer.parseInt(st.nextToken()); }

        Arrays.sort(arr);
        int sum = 1;
        int i = 0;
        while(sum >= arr[i]) {
            sum += arr[i];
            i += 1;
        }

        bw.write(String.valueOf(sum));

        br.close();
        bw.flush();
        bw.close();
    }
}

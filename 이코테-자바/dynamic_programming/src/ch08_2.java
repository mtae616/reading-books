import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ch08_2 {

    public static int[] d = new int[30001];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int x = Integer.parseInt(bf.readLine());
        for (int i = 2; i <= x; i++) {
            d[i] = d[i - 1] + 1;
            if (i % 2 == 0) d[i] = Math.min(d[i], d[i / 2] + 1);
            if (i % 3 == 0) d[i] = Math.min(d[i], d[i / 3] + 1);
            if (i % 5 == 0) d[i] = Math.min(d[i], d[i / 5] + 1);
            for (int a = 0; a < 27; a++) {
                System.out.print(d[a]);
            }
            System.out.println();
        }
        System.out.println(d[x]);
        bf.close();
    }
}

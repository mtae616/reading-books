import java.io.*;
import java.util.Scanner;

public class ch18_42 {

    public static int[] parent = new int[100001];

    public static int findParent(int x) {
        if (x != parent[x]) parent[x] = findParent(parent[x]);
        return parent[x];
    }

    public static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (b > a) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        for (int i = 0; i <= g; i++) parent[i] = i;

        int sum = 0;
        for (int i = 0; i < p; i++) {
            int n = Integer.parseInt(br.readLine());
            int parent = findParent(n);
            if (parent == 0) break;
            unionParent(parent, parent - 1);
            sum += 1;
        }

        bw.write(String.valueOf(sum));

        bw.flush();
        br.close();
        bw.close();
    }

}

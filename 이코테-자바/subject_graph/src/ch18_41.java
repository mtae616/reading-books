import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ch18_41 {

    public static int n, m;
    public static int[] parent = new int[501];

    public static int findParent(int[] parent, int x){
        if (parent[x] != x) parent[x] = findParent(parent, parent[x]);
        return parent[x];
    }

    public static void unionParent(int[] parent, int a, int b) {
        a = findParent(parent, a);
        b = findParent(parent, b);

        if (b > a) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bf.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        for(int i = 0; i < 501; i++) parent[i] = i;

        for (int i = 1; i <= n; i++) {
            String[] s1 = bf.readLine().split(" ");
            for (int j = 1; j <= s1.length; j++) {
                if (Integer.parseInt(s1[j - 1]) == 1) {
                    unionParent(parent, i, j);
                }
            }
        }

        String[] answer = bf.readLine().split(" ");
        boolean flag = true;
        for (int i = 0; i < answer.length - 1; i++) {
            if (findParent(parent, Integer.parseInt(answer[i])) != findParent(parent, Integer.parseInt(answer[i + 1])))
                flag = false;
        }
        System.out.println(flag ? "YES" : "NO");

        bf.close();
    }
}
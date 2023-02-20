import java.util.Scanner;

public class ch10_02 {

    public static int n,m;
    public static int[] parent = new int[100001];

    public static int findParent(int[] parent, int x) {
        if (parent[x] != x) parent[x] = findParent(parent, parent[x]);
        return parent[x];
    }

    public static void unionParent(int[] parent, int a, int b) {
        a = findParent(parent, a);
        b = findParent(parent, b);
        if (b > a) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i <= n; i++) { parent[i] = i; }

        for (int i = 0; i < m; i++) {
            int cal = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (cal == 0) unionParent(parent, a, b);
            else {
                if (findParent(parent, a) == findParent(parent, b)) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }
}

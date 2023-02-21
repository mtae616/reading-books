import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ch13_15 {

    public static int n, m, k, x;
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static int[] visited = new int[300001];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bf.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        k = Integer.parseInt(s[2]);
        x = Integer.parseInt(s[3]);

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            visited[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            String[] s1 = bf.readLine().split(" ");
            int a = Integer.parseInt(s1[0]);
            int b = Integer.parseInt(s1[1]);
            graph.get(a).add(b);
        }

        visited[x] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        while (!q.isEmpty()) {
            Integer now = q.poll();
            for (int i = 0; i < graph.get(now).size(); i++) {
                Integer next = graph.get(now).get(i);
                if (visited[next] == -1) {
                    visited[next] = visited[now] + 1;
                    q.offer(next);
                }
            }
        }

        boolean flag = false;
        for (int i = 1; i <= n; i++) {
            if (visited[i] == k) {
                System.out.println(i);
                flag = true;
            }
        }

        if (!flag) System.out.println(-1);

        bf.close();
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ch10_04 {

    public static int v, e;
    public static int[] times = new int[501];
    public static int[] indegree = new int[501];
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void topologySort() {
        int[] result = new int[501];
        for (int i = 1; i <= v; i++) {
            result[i] = times[i];
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= v; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i = 0; i < graph.get(now).size(); i++) {
                result[graph.get(now).get(i)] = Math.max(result[graph.get(now).get(i)], result[now] + times[graph.get(now).get(i)]);
                indegree[graph.get(now).get(i)] -= 1;
                if (indegree[graph.get(now).get(i)] == 0) {
                    q.offer(graph.get(now).get(i));
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            System.out.println(result[i]);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt();

        for (int i = 0; i <= v; i++) graph.add(new ArrayList<Integer>());

        for (int i = 1; i <= v; i++) {
            int x = sc.nextInt();
            times[i] = x;
            while (true) {
                x = sc.nextInt();
                if (x == -1) break;
                indegree[i] += 1;
                graph.get(x).add(i);
            }
        }

        topologySort();
    }
}

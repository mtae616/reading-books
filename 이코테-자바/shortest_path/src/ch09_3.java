import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node>{
    int idx;
    int distance;

    public Node(int idx, int distance) {
        this.idx = idx;
        this.distance = distance;
    }

    public int getIdx() {
        return idx;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Node o) {
        if (this.distance < o.distance) return -1;
        return 1;
    }
}

public class ch09_3 {

    public static int INF = (int) 1e9;
    public static int n, m, c;
    public static int[] d = new int[30001];
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void dijkstra(int start) {
        d[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int dist = node.getDistance();
            int now = node.getIdx();
            if (d[now] < dist) continue;

            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).getDistance();
                if (cost < d[graph.get(now).get(i).getIdx()]) {
                    d[graph.get(now).get(i).getIdx()] = cost;
                    pq.offer(new Node(graph.get(now).get(i).getIdx(), cost));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        c = sc.nextInt();

        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        Arrays.fill(d, INF);
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int z = sc.nextInt();
            graph.get(a).add(new Node(b, z));
        }
        dijkstra(c);
        int count = 0;
        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            if (d[i] != INF) {
                count += 1;
                maxDistance = Math.max(maxDistance, d[i]);
            }
        }

        System.out.println((count - 1) + " " + maxDistance);
    }
}

// x -> y 일방향

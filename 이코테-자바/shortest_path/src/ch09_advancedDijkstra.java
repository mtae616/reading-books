import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node2 implements Comparable<Node2> {
    private int index;
    private int distance;

    public Node2(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return index;
    }

    public int getDistance() {
        return distance;
    }


    @Override
    public int compareTo(Node2 o) {
        return 0;
    }
}

public class ch09_advancedDijkstra {
    public static final int INF = 2147483647;
    public static int n, m, start;
    public static ArrayList<ArrayList<Node2>> graph = new ArrayList<>();
    public static int[] d = new int[100001];

    public static void dijkstra(int start) {
        PriorityQueue<Node2> pq = new PriorityQueue<>();
        pq.offer(new Node2(start, 0));
        d[start] = 0;
        while (!pq.isEmpty()) {
            Node2 node = pq.poll();
            int dist = node.getDistance();
            int now = node.getIndex();
            if (d[now] < dist) continue;

            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).getDistance();
                if (cost < d[graph.get(now).get(i).getIndex()]) {
                    d[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer(new Node2(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        start = sc.nextInt();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node2>());
        }
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(new Node2(b, c));
        }

        Arrays.fill(d, INF);
        dijkstra(start);
        for (int i = 1; i <= n; i++) {
            if (d[i] == INF) System.out.println("INFINITY");
            else System.out.println(d[i]);
        }
    }
}

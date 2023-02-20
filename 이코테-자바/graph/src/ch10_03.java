import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Node implements Comparable<Node>{
    private int distance;
    private int nodeA;
    private int nodeB;

    public Node(int distance, int nodeA, int nodeB) {
        this.distance = distance;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public int getDistance() {
        return distance;
    }

    public int getNodeA() {
        return nodeA;
    }

    public int getNodeB() {
        return nodeB;
    }

    @Override
    public int compareTo(Node o) {
        if (distance < o.distance) return -1;
        return 1;
    }
}

public class ch10_03 {

    public static int n, m;
    public static int[] parent = new int[100001];
    public static ArrayList<Node> arr = new ArrayList<>();

    public static int findParent(int x) {
        if (parent[x] != x) parent[x] = findParent(parent[x]);
        return parent[x];
    }

    public static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (b > a) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for(int i = 0; i <= n; i++) parent[i] = i;

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            arr.add(new Node(cost, a, b));
        }

        Collections.sort(arr);
        int result = 0;
        int last = 0;
        for (int i = 0; i < arr.size(); i++) {
            int a = arr.get(i).getNodeA();
            int b = arr.get(i).getNodeB();
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += arr.get(i).getDistance();
                last = arr.get(i).getDistance();
            }
        }
        System.out.println(result - last);
    }
}

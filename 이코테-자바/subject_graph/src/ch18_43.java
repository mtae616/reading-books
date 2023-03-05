import java.io.*;
import java.util.*;

class Edges implements Comparable<Edges> {
    int a;
    int b;
    int distance;

    public Edges(int a, int b, int distance) {
        this.a = a;
        this.b = b;
        this.distance = distance;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Edges o) {
        if (this.distance < o.distance) return -1;
        return 1;
    }
}

public class ch18_43 {

    public static int[] parent = new int[200001];
    public static ArrayList<Edges> graph = new ArrayList<>();

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

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) parent[i] = i;

        int total = 0;
        for (int i = 0; i < m; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            int dist = Integer.parseInt(st2.nextToken());
            graph.add(new Edges(a, b, dist));
            total += dist;
        }

        Collections.sort(graph);

        int sum = 0;
        for (int i = 0; i < graph.size(); i++) {
            Edges now = graph.get(i);
            int a = now.getA();
            int b = now.getB();
            int dist = now.getDistance();
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                sum += dist;
            }
        }

        bw.write(String.valueOf(total - sum));

        bw.flush();
        bw.close();
        br.close();
    }
}

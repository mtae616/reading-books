import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {

    private int i;
    private int j;
    private int distance;

    public Node(int i, int j, int distance) {
        this.i = i;
        this.j = j;
        this.distance = distance;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Node o) {
        if (this.distance < o.distance) return -1;
        else return 1;
    }
}


public class ch17_39 {
    public static final int INF = (int) 1e9;
    public static int tc, n;
    public static int[][] map = new int[126][126];
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void dijkstra() {
        int[][] d = new int[n][n];

        for (int i = 0; i < n; i++) Arrays.fill(d[i], INF);

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(0, 0, map[0][0]));
        d[0][0] = map[0][0];
        while (!q.isEmpty()) {
            Node node = q.poll();
            int dist = node.getDistance();
            int i = node.getI();
            int j = node.getJ();
            if (d[i][j] < dist) continue;
            for (int k = 0; k < 4; k++) {
                int ny = dy[k] + i;
                int nx = dx[k] + j;
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                int cost = map[ny][nx] + dist;
                if (cost < d[ny][nx]) {
                    d[ny][nx] = cost;
                    q.offer(new Node(ny, nx, cost));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(d[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            n = Integer.parseInt(br.readLine());
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int k = 0;
                while (st.hasMoreTokens()) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                    k++;
                }
            }
            dijkstra();
        }
        bw.flush();
        br.close();
        bw.close();
    }
}

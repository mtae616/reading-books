import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    private int value;
    private int time;
    private int i;
    private int j;

    public Node(int value, int time, int i, int j) {
        this.value = value;
        this.time = time;
        this.i = i;
        this.j = j;
    }

    public int getValue() {
        return value;
    }

    public int getTime() {
        return time;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public int compareTo(Node o) {
        if (this.value < o.value) return -1;
        return 1;
    }
}



public class ch13_17 {

    public static int[][] arr = new int[201][201];
    public static ArrayList<Node> viruses = new ArrayList<>();
    public static int n, m, s, x, y;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void dfs(int iter) throws IOException {
        if (iter == s) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            bw.write(String.valueOf(arr[x - 1][y - 1]));

            bw.flush();
            bw.close();
            return;
        }
        Collections.sort(viruses);

        Queue<Node> q = new LinkedList<>();
        for(int i = 0; i < viruses.size(); i++) q.offer(viruses.get(i));

        while (!q.isEmpty()) {
            Node now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.getI();
                int nx = dx[i] + now.getJ();
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (arr[ny][nx] == 0) {
                    arr[ny][nx] = now.getValue();
                    viruses.add(new Node(now.getValue(), now.getTime() + 1, ny, nx));
                }
            }
        }

        dfs(iter + 1);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            StringTokenizer lines = new StringTokenizer(br.readLine(), " ");
            int j = 0;
            while (lines.hasMoreTokens()) {
                arr[i][j] = Integer.parseInt(lines.nextToken());
                if (arr[i][j] != 0) {
                    viruses.add(new Node(arr[i][j], 0, i, j));
                }
                j += 1;
            }
        }
        Collections.sort(viruses);

        StringTokenizer options = new StringTokenizer(br.readLine(), " ");
        s = Integer.parseInt(options.nextToken());
        x = Integer.parseInt(options.nextToken());
        y = Integer.parseInt(options.nextToken());

        dfs(0);

        br.close();
    }
}

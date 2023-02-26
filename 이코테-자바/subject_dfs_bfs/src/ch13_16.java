import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Node22 {
    int i;
    int j;

    public Node22(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}

public class ch13_16 {

    public static int result = 0;
    public static int n, m;
    public static int[][] arr = new int[9][9];
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void check() {
        int sum = 0;
        int[][] clone = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                clone[i][j] = arr[i][j];
            }
        }

        Queue<Node22> q = new LinkedList<>();

        for (int i = 0; i < clone.length; i++) {
            for (int j = 0; j < clone[i].length; j++) {
                if (clone[i][j] == 2) { q.offer(new Node22(i, j)); }
            }
        }

        while (!q.isEmpty()) {
            Node22 now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.getJ();
                int ny = dy[i] + now.getI();
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) { continue; }
                if (clone[ny][nx] == 0) {
                    clone[ny][nx] = 2;
                    q.offer(new Node22(ny, nx));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (clone[i][j] == 0) sum += 1;
            }
        }
        result = Math.max(result, sum);
    }

    public static void dfs(int iter) {
        if (iter == 3) {
            check();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    dfs(iter + 1);
                    arr[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < temp.length; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }
        dfs(0);
        bw.write(String.valueOf(result));

        bw.flush();
        br.close();
        bw.close();
    }
}

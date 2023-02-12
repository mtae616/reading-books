import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    private int x;
    private int y;
    private int cnt;

    public Node(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCnt() {
        return cnt;
    }
}

public class ch05_4 {
    public static int n;
    public static int m;
    public static int distance = 2147483647;
    public static int[][] map;
    public static int[][] visited;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        sc.nextLine();
        map = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[]temp = sc.nextLine().split("");
            for (int j = 0; j < temp.length; j++) {
                map[i][j] = temp[j].charAt(0) - '0';
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1));
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.getY() == n - 1 && node.getX() == m - 1) distance = Math.min(distance, node.getCnt());
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + node.getX();
                int ny = dy[i] + node.getY();
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (map[ny][nx] == 1 && visited[ny][nx] == 0) {
                    q.offer(new Node(nx, ny, node.getCnt() + 1));
                    visited[ny][nx] = 1;
                }
            }
        }

        System.out.println(distance);
    }
}

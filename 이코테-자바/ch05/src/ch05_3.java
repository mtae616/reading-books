import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ch05_3 {

    public static int sum = 0;
    public static int n,m;
    public static int[][] map ;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] split = sc.nextLine().split("");
            for (int j = 0; j < split.length; j++) {
                map[i][j] = split[j].charAt(0) - '0';
            }
        }

        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    q.offer(new Point(j, i));
                    while (!q.isEmpty()) {
                        Point po = q.poll();
                        map[po.y][po.x] = 1;
                        for (int k = 0; k < 4; k++) {
                            int nx = dx[k] + po.x;
                            int ny = dy[k] + po.y;
                            if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                            if (map[ny][nx] == 0) q.add(new Point(nx, ny));
                        }
                    }
                    sum += 1;
                }
            }
        }

        System.out.println(sum);
    }
}

import java.util.PriorityQueue;

public class ch12_61 {
    static class Solution {
        public static int[] dx = {0, 1, 0, -1};
        public static int[] dy = {1, 0, -1, 0};

        static class Node implements Comparable<Node>{
            int x;
            int y;
            int value;

            public Node(int x, int y, int value) {
                this.x = x;
                this.y = y;
                this.value = value;
            }

            @Override
            public int compareTo(Node o) {
                if (value < o.value) return -1;
                return 1;
            }
        }

        public static int solution(int[][] maps) {
            int n = maps.length;
            int m = maps[0].length;
            int[][] visited = new int[n][m];

            PriorityQueue<Node> q = new PriorityQueue<>();
            q.offer(new Node(0, 0, 0));
            visited[0][0] = 1;

            while (!q.isEmpty()) {
                Node now = q.poll();
                if (now.y == (n - 1) && now.x == (m - 1)) return now.value + 1;
                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                    if (maps[ny][nx] == 1 && visited[ny][nx] == 0) {
                        q.offer(new Node(nx, ny, now.value + 1));
                        visited[ny][nx] = 1;
                    }
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution.solution(new int[][]{
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1,1,1,0,0},
                {0, 0, 0, 0, 1}
        }));
    }
}

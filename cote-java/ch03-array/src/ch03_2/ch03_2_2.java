package ch03_2;

public class ch03_2_2 {

    static class Solution {

        public static int[] solution(int n) {
            int[][] map = new int[n][n];

            int idx = 1;
            int x = 0;
            int y = 0;
            while (true) {
                while (true) {
                    map[y][x] = idx++;
                    if (y + 1 == n || map[y + 1][x] != 0) break;
                    y += 1;
                }
                if (x + 1 == n || map[y][x + 1] != 0) break;
                x += 1;

                while (true) {
                    map[y][x] = idx++;
                    if (x + 1 == n || map[y][x + 1] != 0) break;
                    x += 1;
                }
                if (map[y - 1][x - 1] != 0) break;
                x -= 1;
                y -= 1;

                while (true) {
                    map[y][x] = idx++;
                    if (map[y - 1][x - 1] != 0) break;
                    x -= 1;
                    y -= 1;
                }
                if (y + 1 == n || map[y + 1][x] != 0) break;
                y += 1;
            }
            int[] answer = new int[idx - 1];
            int mapIdx = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    answer[mapIdx++] = map[i][j];
                }
            }
            return answer;
        }
    }


    public static void main(String[] args) {
        Solution.solution(4);
    }
}

import java.util.Arrays;

public class ch10_39 {
//    static class Solution {
//        public static int solution(int[][] triangle) {
//            for (int i = 1; i < triangle.length; i++) {
//                for (int j = 0; j < triangle[i].length; j++) {
//                    if (j == 0) {
//                        triangle[i][j] += triangle[i - 1][j];
//                        continue;
//                    } else if (i == j) {
//                        triangle[i][j] += triangle[i - 1][j - 1];
//                        continue;
//                    }
//
//                    int upLeft = triangle[i - 1][j - 1];
//                    int up = triangle[i - 1][j];
//                    triangle[i][j] += Math.max(up, upLeft);
//                }
//            }
//
//            return Arrays.stream(triangle[triangle.length - 1]).max().getAsInt();
//        }
//    }

    static class Solution {
        private static final int[][] mem = new int[501][501];

        private static int max(int x, int y, int[][] triangle) {
            if (y == triangle.length) return 0;
            if (mem[x][y] != -1) return mem[x][y];

            return mem[x][y] = triangle[y][x] + Math.max(
                    max(x, y + 1, triangle),
                    max(x + 1, y + 1, triangle)
            );
        }

        public static int solution(int[][] triangle) {
            for (int[] row : mem) Arrays.fill(row, -1);
            return max(0, 0, triangle);
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[][]{
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        });
    }
}

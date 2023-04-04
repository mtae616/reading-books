package ch05_15;

public class ch05_15_2 {
    static class Solution {
        static int zCnt, oCnt;

        public static void quard(int[][] arr, int y, int x, int length) {
            int buf = arr[y][x];
            for (int i = y; i < y + length; i++) {
                for (int j = x; j < x + length; j++) {
                    if (buf != arr[i][j]) {
                        int newLength = length / 2;
                        quard(arr, y, x, newLength);
                        quard(arr, y, x + newLength, newLength);
                        quard(arr, y + newLength, x, newLength);
                        quard(arr, y + newLength, x + newLength, newLength);
                        return;
                    }
                }
            }

            if (buf == 0) zCnt += 1;
            if (buf == 1) oCnt += 1;
        }

        static int[] solution(int[][] arr) {
            int[] answer = new int[arr.length];
            quard(arr, 0, 0, arr.length);
            return new int[]{zCnt, oCnt};
        }
    }


    public static void main(String[] args) {
        Solution.solution(new int[][]{
                {1, 1, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 1},
                {1, 1, 1, 1}
        });
    }
}

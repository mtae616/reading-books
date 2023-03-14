package ch05_15;

class Solution {
    public static int zCnt;
    public static int oCnt;

    public static void quard(int[][] arr, int beginY, int beginX, int length) {
        int start = arr[beginY][beginX];

        int half = length / 2;
        for (int i = beginY; i < beginY + length; i++) {
            for (int j = beginX; j < beginX + length; j++) {
                if (start != arr[i][j]) {
                    quard(arr, beginY, beginX, half); // 2
                    quard(arr, beginY, beginX + half, half); // 1
                    quard(arr, beginY + half, beginX, half); // 3
                    quard(arr, beginY + half, beginX + half, half); // 4
                    return;
                }
            }
        }

        if (start == 0) zCnt += 1;
        else oCnt += 1;
    }

    public static int[] solution(int[][] arr) {
        int[] answer = new int[2];

        quard(arr, 0, 0, arr.length);
        return new int[] {zCnt, oCnt};
    }
}

public class ch05_15_1 {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 1, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 1},
                {1, 1, 1, 1}
        };

        Solution.solution(arr);
    }
}

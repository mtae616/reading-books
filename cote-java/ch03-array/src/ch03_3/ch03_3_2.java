package ch03_3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ch03_3_2 {

    static class Solution {
        public static final int LENGTH = 5;
        public static int[] dx = {0, 1, 0, -1};
        public static int[] dy = {1, 0, -1, 0};
        static char[][] placeMap = new char[LENGTH][LENGTH];

        public static boolean checkPerson(int ny, int nx, int i, int j) {
            for (int k = 0; k < 4; k++) {
                int nny = ny + dy[k];
                int nnx = nx + dx[k];

                if (nny < 0 || nnx < 0 || nny >= LENGTH || nnx >= LENGTH) continue;
                if (nny == i && nnx == j) continue;
                if (placeMap[nny][nnx] == 'P') return false;
            }
            return true;
        }

        public static boolean check(int i, int j) {
            for (int k = 0; k < 4; k++) {
                int ny = i + dy[k];
                int nx = j + dx[k];
                if (ny < 0 || nx < 0 || ny >= LENGTH || nx >= LENGTH) continue;
                if (placeMap[ny][nx] == 'O') {
                    if (!checkPerson(ny, nx, i, j)) return false;
                } else if (placeMap[ny][nx] == 'P') {
                    return false;
                }
            }
            return true;
        }

        public static boolean waitngRoom(String[] place) {
            for (int i = 0; i < LENGTH; i++) placeMap[i] = place[i].toCharArray();

            for (int i = 0; i < LENGTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    if (placeMap[i][j] == 'P') {
                        if (!check(i, j)) return false;
                    }
                }
            }
            return true;
        }
        public static int[] solution(String[][] places) {
            // P 자리
            // O 빈테이블
            // X 파티션
            int[] answer = new int[LENGTH];
            int idx = 0;
            for (String[] place : places) {
                if (!waitngRoom(place)) answer[idx++] = 0;
                else answer[idx++] = 1;
            }

            System.out.println(Arrays.toString(answer));

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution(new String[][]{
                {
                "OOPOO",
                "OPOOO",
                "OOOOO",
                "OOOOO",
                "OOOOO"
                },

                {
                 "POOPX",
                 "OXPXP",
                 "PXXXO",
                 "OXXXO",
                 "OOOPP"
                },

                {
                 "PXOPX",
                 "OXOXP",
                 "OXPOX",
                 "OXXOP",
                 "PXPOX"
                },

                {
                 "OOOXX",
                 "XOOOX",
                 "OOOXX",
                 "OXOOX",
                 "OOOOO"
                },

                {
                 "PXPXP",
                 "XPXPX",
                 "PXPXP",
                 "XPXPX",
                 "PXPXP"
                }
        });
    }
}

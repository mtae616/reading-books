package ch03_01;//https://school.programmers.co.kr/learn/courses/30/lessons/87377

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ch03_1_1 {

    static class Solution {
       static class Node{
            long y;
            long x;

            public Node(long y, long x) {
                this.y = y;
                this.x = x;
            }

            public long getY() {
                return y;
            }

            public long getX() {
                return x;
            }
        }

        public static Node getMax(List<Node> arr) {
            long maxX = Long.MIN_VALUE;
            long maxY = Long.MIN_VALUE;
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getX() > maxX) maxX = arr.get(i).getX();
                if (arr.get(i).getY() > maxY) maxY = arr.get(i).getY();
            }
            return new Node(maxY, maxX);
        }

        public static Node getMin(List<Node> arr) {
            long minX = Long.MAX_VALUE;
            long minY = Long.MAX_VALUE;
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getX() < minX) minX = arr.get(i).getX();
                if (arr.get(i).getY() < minY) minY = arr.get(i).getY();
            }
            return new Node(minY, minX);
        }


        public static String[] solution(int[][] line) {
            List<Node> cordi = new ArrayList<>();

            for (int i = 0; i < line.length; i++) {
                for (int j = i + 1; j < line.length; j++) {
                    int[] arr1 = line[i];
                    long a1 = arr1[0];
                    long b1 = arr1[1];
                    long c1 = arr1[2];

                    int[] arr2 = line[j];
                    long a2 = arr2[0];
                    long b2 = arr2[1];
                    long c2 = arr2[2];
                    double x = (double) ((b1 * c2) - (b2 * c1)) / (a1 * b2 - a2 * b1);
                    double y = (double) (a2 * c1 - a1 * c2) / (a1 * b2 - a2 * b1);
                    if (x == (long) x && y == (long) y) {
                        cordi.add(new Node((long) y, (long) x));
                    }
                }
            }

            Node maxNode = getMax(cordi);
            Node minNode = getMin(cordi);

            int mapY = (int) (maxNode.getY() - minNode.getY());
            int mapX = (int) (maxNode.getX() - minNode.getX());
            char[][] map = new char[mapY + 1][mapX + 1];
            for(int i = 0; i < map.length; i++) Arrays.fill(map[i], '.');

            for (int i = 0; i < cordi.size(); i++) {
                Node now = cordi.get(i);
                int y = (int) (maxNode.getY() - now.getY());
                int x = (int) (now.getX() - minNode.getX());
                map[y][x] = '*';
            }
            String[] answer = new String[map.length];

            for (int i = 0; i < map.length; i++) answer[i] = new String(map[i]);

            return answer;
        }
    }


    public static void main(String[] args) {
        int[][] arr = {
                {2, -1, 4},
                {-2, -1, 4},
                {0, -1, 1},
                {5, -8, -12},
                {5, 8, 12}
        };

//        int[][] arr = {
//                {0, 1, -1},
//                {1, 0, -1},
//                {1, 0, 1}
//        };

//        int[][] arr = {
//                {1, -1, 0},
//                {2, -1, 0},
//                {4, -1, 0}
//        };

        Solution.solution(arr);
    }
}

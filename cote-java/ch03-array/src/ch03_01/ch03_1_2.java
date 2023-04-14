package ch03_01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class ch03_1_2 {

    static class Solution {
        static class Node {
            long x;
            long y;

            public Node(long x, long y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "x=" + x +
                        ", y=" + y +
                        '}';
            }
        }

        public static long getMax(long[] list) {
            return Arrays.stream(list).max().getAsLong();
        }

        public static long getMin(long[] list) {
            return Arrays.stream(list).min().getAsLong();
        }


        public static String[] solution(int[][] line) {
            Set<Node> set = new HashSet<>();

            for (int i = 0; i < line.length; i++) {
                for (int j = i + 1; j < line.length; j++) {
                    long a = line[i][0];
                    long b = line[i][1];
                    long e = line[i][2];

                    long c = line[j][0];
                    long d = line[j][1];
                    long f = line[j][2];

                    double x = (double) ((b * f) - (e * d)) / ((a * d) - (b * c));
                    double y = (double) ((e * c) - (a * f)) / ((a * d) - (b * c));
                    if (x == (long) x && y == (long) y) {
                        set.add(new Node((long) x, (long) y));
                    }
                }
            }

            long[] xList = set.stream().mapToLong(e -> e.x).toArray();
            long[] yList = set.stream().mapToLong(e -> e.y).toArray();
            long xMax = getMax(xList);
            long yMax = getMax(yList);

            long xMin = getMin(xList);
            long yMin = getMin(yList);

            int height = (int) ((int) yMax - yMin);
            int width = (int) ((int) xMax - xMin);
            char[][] map = new char[height + 1][width + 1];
            for(int i = 0; i < map.length; i++) Arrays.fill(map[i], '.');

            Iterator<Node> iter = set.iterator();
            while (iter.hasNext()) {
                Node el = iter.next();
                map[(int) (yMax - el.y)][(int) (el.x - xMin)] = '*';
            }

            String[] answer = new String[map.length];
            for (int i = 0; i < map.length; i++) answer[i] = new String(map[i]);

            return answer;
        }
    }


    public static void main(String[] args) {
        Solution.solution(new int[][]{
                {1, -1, 0},
                {2, -1, 0},
                {4, -1, 0}
        });
    }
}

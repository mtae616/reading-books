package ch09_33;

import java.util.HashSet;
import java.util.Set;

public class ch09_33_2 {
    static class Solution {
        public static double getInclination(int[] dot1, int[] dot2) {
            return (double) (dot2[1] - dot1[1]) / (dot2[0] - dot1[0]);
        }
        public static int solution(int[][] dots) {
            Set<Double> inclinations = new HashSet<>();
            for (int i = 0; i < dots.length; i++) {
                for (int j = i + 1; j < dots.length; j++) {
                    double inclination = getInclination(dots[i], dots[j]);
                    System.out.println(inclination);
                    if (inclinations.contains(inclination)) return 1;
                    inclinations.add(inclination);
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[][]{
                {1, 4},
                {9, 2},
                {3, 8},
                {11, 6}
        });
    }
}

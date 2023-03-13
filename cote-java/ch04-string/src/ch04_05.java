//https://school.programmers.co.kr/learn/courses/30/lessons/12932

import java.util.Arrays;

class Solution {
    public static int[] solution(long n) {
        StringBuilder reverse = new StringBuilder(String.valueOf(n)).reverse();

        int[] answer = new int[reverse.length()];
        int i = 0;
        while (i < reverse.length()) answer[i] = reverse.charAt(i++) - '0';

        return answer;
    }
}

public class ch04_05 {
    public static void main(String[] args) {
        long arr = 12345L;
        System.out.println(Arrays.toString(Solution.solution(arr)));
    }
}

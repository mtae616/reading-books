package ch09_34;

import java.util.HashSet;

public class ch09_34 {
    static class Solution {
        public static String solution(String my_string) {
            String answer = "";
            HashSet<Character> set = new HashSet<>();

            for (Character c : my_string.toCharArray()) {
                if (set.contains(c)) continue;
                set.add(c);
                answer += c;
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution("We are the world");
    }
}

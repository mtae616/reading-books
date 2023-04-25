package ch09_34;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class ch09_34_2 {

    static class Solution {
        public static String solution(String myString) {
            Set<Character> temp = new HashSet<>();

            char[] chars = myString.toCharArray();
            String answer = "";
            for (char c : chars) {
                if (temp.contains(c)) continue;
                temp.add(c);
                answer += c;
            }

            return answer;
        }
    }


    public static void main(String[] args) {
        Solution.solution("people");
    }
}

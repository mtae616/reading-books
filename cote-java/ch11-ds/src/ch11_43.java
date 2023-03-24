import java.util.Stack;

public class ch11_43 {
    static class Solution {

        public static boolean isRight(String s) {
            Stack<Character> l1 = new Stack<>(); // ()
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '(') l1.add(c);
                else if (c == ')') {
                    if (l1.isEmpty()) return false;
                    if (l1.pop() != '(') return false;
                }

                if (c == '[') l1.add(c);
                else if (c == ']') {
                    if (l1.isEmpty()) return false;
                    if (l1.pop() != '[') return false;
                }

                if (c == '{') l1.add(c);
                else if (c == '}') {
                    if (l1.isEmpty()) return false;
                    if (l1.pop() != '{') return false;
                }

            }
            if (l1.size() > 0) return false;
            return true;
        }
        public static int solution(String s) {
            int answer = 0;

            int length = s.length();
            if (isRight(s)) answer += 1;

            for (int i = 0; i < length - 1; i++) {
                s = s.substring(1) + s.charAt(0);
                if(isRight(s)) answer += 1;
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution("()(");
    }
}

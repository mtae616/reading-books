import java.util.Stack;

class Solution {
    public static boolean solution(String s) {
        boolean answer = true;
        Stack<Character> temp = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '(') temp.push(c);
            else {
                if (temp.size() < 1) return false;
                Character ch = temp.pop();
                if (ch != '(') return false;
            }

            i += 1;
        }
        if (temp.size() > 0) return false;
        return true;
    }
}

public class ch11_42 {
    public static void main(String[] args) {
        Solution.solution("()()");
    }
}

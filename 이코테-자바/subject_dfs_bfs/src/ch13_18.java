import java.util.Stack;

public class ch13_18 {

    static boolean check(String p) {
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') count += 1;
            else {
                if (count == 0) {
                    return false;
                }
                count -= 1;
            }
        }
        return true;
    }

    static int split(String p) {
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') count += 1;
            else count -= 1;
            if (count == 0) return i;
        }
        return -1;
    }

    public static String solution(String p) {
        String answer = "";

        if (p.equals("")) return answer;
        int idx = split(p);

        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        if (check(u)) {
            answer = u + solution(v);
        } else {
            answer = "(";
            answer += solution(v);
            answer += ")";
            u = u.substring(1, u.length() - 1);
            String temp = "";
            for (int i = 0; i < u.length(); i++) {
                if (u.charAt(i) == '(') temp += ")";
                else temp += "(";
            }
            answer += temp;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("()))((()"));
    }
}

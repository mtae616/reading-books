public class ch04_8 {
    static class Solution {
        public static int solution(String s) {
            int answer = (int) 1e9;
            if (s.length() == 1) return s.length();
            for (int i = 1; i < s.length(); i++) {
                String com = "";
                int j = 0;

                String buf = "";
                int sum = 0;
                while (j < s.length()) {
                    String sub = s.substring(j, Math.min(j + i, s.length()));
                    if (buf.equals(sub)) {
                        sum += 1;
                    } else {
                        if (sum != 0) com += (sum + 1);
                        com += buf;
                        buf = sub;
                        sum = 0;
                    }
                    j += i;
                }
                if (sum != 0) com += (sum + 1);
                com += buf;
                answer = Math.min(answer, com.length());
            }
            return answer;
        }
    }
    public static void main(String[] args) {
        Solution.solution("a");
    }
}

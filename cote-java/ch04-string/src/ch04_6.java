public class ch04_6 {
    static class Solution {
        public static String solution(String s, int n) {
            String answer = "";
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    c += n;
                    if (c > 'z') c = (char) (('a' + (c - 'z')) - 1);
                }
                if (c >= 'A' && c <= 'Z') {
                    c += n;
                    if (c > 'Z') c = (char) (('A' + (c - 'Z')) - 1);
                }
                answer += c;
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution("z", 3);
    }
}

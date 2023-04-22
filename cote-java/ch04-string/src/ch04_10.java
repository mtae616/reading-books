public class ch04_10 {
    static class Solution {
        public static int[] solution(String s) {
            int idx = 0;
            int replacedZCnt = 0;
            String buf = s;
            while (true) {
                replacedZCnt += buf.chars().filter(c -> c == '0').count();
                String replace = buf.replace("0", "");
                buf = Integer.toBinaryString(replace.length());
                idx += 1;
                if (buf.equals("1")) break;
            }

            return new int[]{idx, replacedZCnt};
        }
    }

    public static void main(String[] args) {
        Solution.solution("1111111");
    }
}

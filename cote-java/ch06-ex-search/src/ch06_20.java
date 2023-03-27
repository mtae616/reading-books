import java.util.*;

public class ch06_20 {
    static class Solution {
        public static String[][] arr = {
                {"+", "-", "*"},
                {"+", "*", "-"},
                {"-", "+", "*"},
                {"-", "*", "+"},
                {"*", "+", "-"},
                {"*", "-", "+"}
        };

        public static long cal(List<String> tokenList, String[] opList) {
            int idx = 0;
            while (idx < 3) {
                for (int i = 0; i < tokenList.size(); i++) {
                    if (tokenList.get(i).equals(opList[idx])) {
                        long a = Long.parseLong(tokenList.get(i - 1));
                        long b = Long.parseLong(tokenList.get(i + 1));

                        String op = opList[idx];
                        long temp;
                        if (op.equals("+")) temp = a + b;
                        else if (op.equals("-")) temp = a - b;
                        else temp = a * b;

                        tokenList.remove(i - 1);
                        tokenList.remove(i - 1);
                        tokenList.remove(i - 1);
                        tokenList.add(i - 1, String.valueOf(temp));
                        i -= 2;
                    }
                }
                idx += 1;
            }
            return Long.parseLong(tokenList.get(0));
        }

        public static long solution(String expression) {
            long answer = (long) -(1e9);
            StringTokenizer st = new StringTokenizer(expression, "+-*", true);
            ArrayList<String> tokenList = new ArrayList<>();

            while (st.hasMoreTokens()) { tokenList.add(st.nextToken()); }

            for (int i = 0; i < arr.length; i++) answer = Math.max(answer, Math.abs(cal(new ArrayList<>(tokenList), arr[i])));
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution("100-200*300-500+20");
    }
}
import java.util.Arrays;

public class ch12_60 {
    static class Solution {
        public static int answer = (int) 1e9;
        public static int[] visited;

        public static void dfs(int iter, String now, String target, String[] words) {
            if (now.equals(target)) {
                answer = Math.min(answer, iter);
                return;
            }
            if (iter >= words.length) return;

            for (int j = 0; j < words.length; j++) {
                if (visited[j] == 0) {
                    int sum = 0;
                    for (int k = 0; k < words[j].length(); k++) {
                        if (now.charAt(k) == words[j].charAt(k)) sum += 1;
                    }
                    if (sum >= now.length() - 1) {
                        visited[j] = 1;
                        dfs(iter + 1, words[j], target, words);
                        visited[j] = 0;
                    }
                }
            }
        }

        public static int solution(String begin, String target, String[] words) {
            visited = new int[words.length];

            for (int j = 0; j < words.length; j++) {
                int sum = 0;
                for (int k = 0; k < words[j].length(); k++) {
                    if (begin.charAt(k) == words[j].charAt(k)) sum += 1;
                }
                if (sum >= begin.length() - 1) dfs(1, words[j], target, words);
            }
            return answer == (int)1e9 ? 0 : answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution("1234567000", "1234567899", new String[]{
                "1234567800", "1234567890", "1234567899"
        });
    }
}

public class ch09_35 {
    static class Solution {
        public static int solution(String before, String after) {
            int answer = 1;
            int[] arr = new int[26];

            for (int i = 0; i < before.length(); i++) {
                arr[before.charAt(i) - 'a'] += 1;
            }

            for (int j = 0; j < after.length(); j++) {
                arr[after.charAt(j) - 'a'] -= 1;
                if (arr[after.charAt(j) - 'a'] < 0) answer = 0;
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution("allpe", "apple");
    }
}

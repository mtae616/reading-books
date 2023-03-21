public class ch06_19 {
//    static class Solution {
//        public static int[] solution(int brown, int yellow) {
//            int[] answer = {};
//            int area = brown + yellow;
//            int min = (int) 1e9;
//            for (int i = 1; i <= Math.sqrt(area); i++) {
//                for (int j = i; j <= area; j++) {
//                    if (i * j == area && (i - 2) * (j - 2) == yellow) {
//                        return new int[]{j, i};
//                    }
//                }
//            }
//            return answer;
//        }
//    }

    static class Solution {
        public static int[] solution(int brown, int yellow) {
            int[] answer = {};

            for (int i = 3; i <= 5000; i++) {
                for (int j = 3; j <= i; j++) {
                    int boundary = (i + j - 2) * 2;
                    int center = i * j - boundary;
                    if (brown == boundary && yellow == center) return new int[]{i, j};
                }
            }

            return answer;
        }
    }


    public static void main(String[] args) {
        Solution.solution(18, 6);
    }
}

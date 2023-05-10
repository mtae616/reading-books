import java.util.PriorityQueue;

public class ch11_50 {
    static class Solution {
        public static int[] solution(String[] operations) {
            int[] answer = {};

            // I 숫자 삽입
            // D 1 최댓값 삭제
            // D -1 최솟값 삭제
            PriorityQueue<Integer> q = new PriorityQueue<>();

            for (int i = 0; i < operations.length; i++) {
                String[] s = operations[i].split(" ");
                String op = s[0];
                int code = Integer.parseInt(s[1]);
                if (op.charAt(0) == 'I') {
                    q.add(code);
                } else if (op.charAt(0) == 'D' && q.size() < 1) {
                    continue;
                } else {
                    if (code == 1) {
                        Integer integer = q.stream().max((a, b) -> (a - b)).get();
                        q.remove(integer);
                    } else {
                        System.out.println(q.poll());
                    }
                }
            }
            if (q.size() < 1) return new int[]{0, 0};
            Integer max = q.stream().max((a, b) -> a - b).get();
            Integer min = q.poll();
            return new int[]{max, min};
        }
    }

    public static void main(String[] args) {
        Solution.solution(new String[]{
                "I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"
        });
    }
}

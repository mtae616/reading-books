import java.util.*;
import java.util.stream.Collectors;

public class ch11_45 {

//    static class Solution {
//        public static int[] solution(int[] progresses, int[] speeds) {
//            List<Integer> answer = new ArrayList<>();
//            int t = 0;
//            Queue<Integer> q = Arrays.stream(progresses).boxed().collect(Collectors.toCollection(LinkedList::new));
//            Queue<Integer> s = Arrays.stream(speeds).boxed().collect(Collectors.toCollection(LinkedList::new));
//
//            while (!q.isEmpty()) {
//                t += 1;
//                if (q.peek() + (t * s.peek()) < 100) continue;
//
//                int sum = 0;
//                while (!q.isEmpty() && q.peek() + (t * s.peek()) >= 100) {
//                    q.poll();
//                    s.poll();
//                    sum += 1;
//                }
//                answer.add(sum);
//            }
//
//            return answer
//                    .stream()
//                    .mapToInt(e -> e)
//                    .toArray();
//        }
//    }

    static class Solution {
        public static int[] solution(int[] progresses, int[] speeds) {
            List<Integer> answer = new ArrayList<>();
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < progresses.length; i++) q.add(i);

            int days = 0;
            int count = 0;
            while (!q.isEmpty()) {
                int index = q.poll();
                int expiration = (int) Math.ceil((double) (100 - progresses[index]) / speeds[index]);
                if (expiration > days) {
                    if (days != 0) {
                        answer.add(count);
                        count = 0;
                    }
                    days = expiration;
                }
                count++;
            }

            answer.add(count);
            return answer
                    .stream()
                    .mapToInt(e -> e)
                    .toArray();
        }
    }

    public static void main(String[] args) {
        Solution.solution(
                new int[]{93, 30, 55},
                new int[]{1, 30, 5});
    }
}

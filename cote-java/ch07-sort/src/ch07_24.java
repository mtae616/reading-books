import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ch07_24 {
//    static class Solution {
//        public static int[] arr = new int[201];
//        public static int[] solution(int[] numbers) {
//            int[] answer = {};
//            for (int i = 0; i < numbers.length; i++) {
//                for (int j = i; j < numbers.length; j++) {
//                    if (i != j) arr[numbers[i] + numbers[j]] += 1;
//                }
//            }
//
//            List<Integer> temp = new ArrayList<>();
//            for (int i = 0; i < arr.length; i++) {
//                if (arr[i] > 0) temp.add(i);
//            }
//
//            return temp.stream().mapToInt(Integer::intValue).toArray();
//        }
//    }

    static class Solution {
        public static int[] solution(int[] numbers) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < numbers.length; i++) {
                for (int j = i + 1; j < numbers.length; j++) {
                    set.add(numbers[i] + numbers[j]);
                }
            }
            return set.stream().mapToInt(Integer::intValue).sorted().toArray();
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[]{2, 1, 3, 4, 1});
    }
}

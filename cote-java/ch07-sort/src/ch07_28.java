import java.util.Arrays;
import java.util.stream.Collectors;

public class ch07_28 {

    static class Solution {
        public static String solution(int[] numbers) {
            return Arrays.stream(numbers)
                    .mapToObj(String::valueOf)
                    .sorted((s1, s2) -> {
                        int i = Integer.parseInt(s1 + s2);
                        int r = Integer.parseInt(s2 + s1);
                        return r - i;
                    })
                    .collect(Collectors.joining())
                    .replaceAll("^0+", "0");
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[]{0, 0, 0, 0});
    }
}

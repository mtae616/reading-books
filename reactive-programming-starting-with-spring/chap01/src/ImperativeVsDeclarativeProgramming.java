import java.util.Arrays;
import java.util.List;

public class ImperativeVsDeclarativeProgramming {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 21, 10, 8, 11);
        int sum = 0;
        for (Integer i : list) {
            if (i % 2 == 1 && i > 6) {
                sum += i;
            }
        }

        System.out.println("합계 : " + sum);

        int sum2 = list.stream()
                .filter(n -> n > 6 && (n % 2 == 1))
                .mapToInt(n -> n)
                .sum();
        System.out.println("합계 : " + sum2);
    }
}

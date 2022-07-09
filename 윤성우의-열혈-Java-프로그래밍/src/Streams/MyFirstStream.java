package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MyFirstStream {
    public static void main(String[] args) {
        int[] ar = {1, 2, 3, 4, 5};
        IntStream stm1 = Arrays.stream(ar);
        IntStream stm2 = stm1.filter(n -> n % 2 == 1);
//        int[] arr = stm1.filter(n -> n % 2 == 1).toArray();
        int sum = stm2.sum();
        int sum2 = Arrays.stream(ar).filter(n -> n % 2 == 1).sum();
        // stream pipe라 하나에 한 개만 열 수 있는 듯...
        System.out.println(sum);
        System.out.println(sum2);

        String[] names = {"Yoon", "Park", "Kang"};
        Arrays.stream(names).forEach(e -> System.out.println(e));

        int[] arr = {1, 2, 3, 4, 5};
        Arrays.stream(arr).filter(n -> n % 2 == 1).forEach(n -> System.out.println(n));
        List<String> s1 = Arrays.asList("Toy", "Robot", "Box");
        s1.stream().filter(s -> s.length() == 3).forEach(s -> System.out.println(s));
    }
}

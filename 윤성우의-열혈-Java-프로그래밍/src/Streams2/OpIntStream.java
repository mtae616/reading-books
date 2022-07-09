package Streams2;

import java.util.stream.IntStream;

public class OpIntStream {
    public static void main(String[] args) {
        int sum = IntStream.of(1, 3, 5, 7, 9).sum();
        System.out.println(sum);

        System.out.println();

        long cnt = IntStream.of(1, 3, 5, 7, 9).count();
        System.out.println(cnt);

        System.out.println();

        IntStream.of(1, 3, 5, 7, 9).average().ifPresent(av -> System.out.println(av));

        System.out.println();

        IntStream.of(1, 3, 5, 7, 9).min().ifPresent(mn -> System.out.println(mn));
        System.out.println();

        IntStream.of(1, 3, 5, 7, 9).max().ifPresent(mx -> System.out.println(mx));

        boolean b = IntStream.of(1, 2, 3, 4, 5).allMatch(n -> n % 2 == 0);
        System.out.println("모두 짝수이다. " + b);

        b = IntStream.of(1, 2, 3, 4, 5).anyMatch(n -> n % 2 == 0);
        System.out.println("짝수가 하나는 있다. " + b);

        b = IntStream.of(1, 2, 3, 4, 5).noneMatch(n -> n % 2 == 0);
        System.out.println("짝수가 하나도 없다. " + b);
    }
}

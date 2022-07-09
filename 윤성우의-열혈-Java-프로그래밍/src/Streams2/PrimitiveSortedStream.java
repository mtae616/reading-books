package Streams2;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class PrimitiveSortedStream {
    public static void main(String[] args) {
        IntStream.of(3, 9, 4, 2)
                .sorted()
                .forEach(d -> System.out.println(d));
        System.out.println();

        DoubleStream.of(3.3, 6.2, 1.5, 8.3)
                .sorted()
                .forEach(d -> System.out.println(d));
        System.out.println();
    }
}

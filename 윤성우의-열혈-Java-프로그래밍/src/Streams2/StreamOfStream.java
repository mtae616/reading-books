package Streams2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamOfStream {
    public static void main(String[] args) {
        Stream.of(11, 22, 33, 44).forEach(n -> System.out.println(n));
        System.out.println();

        Stream.of("So Simple").forEach(s -> System.out.println(s));
        System.out.println();

        List<String> s1 = Arrays.asList("Toy", "Robot", "Box");
        Stream.of(s1).forEach(w -> System.out.println(w));
    }
}

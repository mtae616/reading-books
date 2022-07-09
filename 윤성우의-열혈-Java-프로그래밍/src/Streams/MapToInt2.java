package Streams;

import java.util.Arrays;
import java.util.List;

public class MapToInt2 {
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("Box", "Robot", "Simple");

        ls.stream().mapToInt(s -> s.length()).forEach(n -> System.out.println(n));

    }
}

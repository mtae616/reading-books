package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Box<T> {
    private T ob;

    public Box(T ob) {
        this.ob = ob;
    }

    public T getOb() {
        return ob;
    }
}

public class Subject29_1 {
    public static void main(String[] args) {
        List<Box<String>> ls = Arrays.asList(new Box<>("Robot"), new Box<>("Simple"));

        ls.stream().filter(e -> e.getOb().length() == 5).forEach(e -> System.out.println(e.getOb()));
        ls.stream().forEach(e -> System.out.println(e.getOb().length()));
    }
}

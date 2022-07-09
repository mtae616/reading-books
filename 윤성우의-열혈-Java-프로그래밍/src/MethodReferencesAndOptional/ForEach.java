package MethodReferencesAndOptional;

import java.util.Arrays;
import java.util.List;

public class ForEach {
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("Box", "Robot");
        ls.forEach(s -> System.out.println(s));

        System.out.println();

        ls.forEach(System.out::println);
    }
}

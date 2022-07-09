package MethodReferencesAndOptional;

import java.util.Locale;
import java.util.Optional;

public class OptionalMap {
    public static void main(String[] args) {
        Optional<String> os1 = Optional.of("Optional String");
        Optional<String> os2 = os1.map(s -> s.toLowerCase());
        System.out.println(os2.get());

        System.out.println();

        Optional<String> os3 = os1.map(s -> s.replace(' ', '-')).map(s -> s.toLowerCase());
        System.out.println(os3.get());
    }
}

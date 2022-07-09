package MethodReferencesAndOptional;

import java.util.Optional;

public class OptionalOrElse {
    public static void main(String[] args) {
        Optional<String> os1 = Optional.empty(); // 빈 optional 인스턴스 생성되어 반환
        // == Optional<String> os1 = Optional.ofNullable(null);
        Optional<String> os2 = Optional.of("So Basic");

        String s1 = os1.map(s -> s.toString()).orElse("Empty"); // 빈 인스턴스 반환 -> Empty 저
        String s2 = os2.map(s -> s.toString()).orElse("Empty");

        System.out.println(s1);
        System.out.println(s2);
    }
}

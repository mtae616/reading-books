package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class ReduceStream {
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("Box", "Simple", "Complex", "Robot");

        BinaryOperator<String> lc = (s1, s2) -> { // 가장 긴 문자열을 반환
            if (s1.length() > s2.length())
                return s1;
            else
                return s2;
        };
        String str = ls.stream().reduce("", lc);
        System.out.println(str);
    }
}

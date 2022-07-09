package LambdaExpression;

import java.util.function.BiPredicate;

public class Subject27_3 {
    public static void main(String[] args) {
        BiPredicate<String, Integer> conv = (s, i) -> s.length() > i;

        if (conv.test("Robot", 3))
            System.out.println("문자열 길이 3 초과");
        else
            System.out.println("문자열 길이 3 이하");

        if (conv.test("Box", 5))
            System.out.println("문자열 길이 5 초과");
        else
            System.out.println("문자열 길이 5 이하");
    }
}

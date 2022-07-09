package LambdaExpression;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        Function<String, Integer> f = s -> s.length();
        System.out.println(f.apply("hello"));
        // String은 input, Integer는 output

        System.out.println();

        Function<Double, Double> cti = d -> d * 0.393701;
        System.out.println(cti.apply(2.0));
    }
}

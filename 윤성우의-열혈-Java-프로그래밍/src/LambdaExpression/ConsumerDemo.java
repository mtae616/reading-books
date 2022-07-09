package LambdaExpression;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> c = s -> System.out.println(s);
        // 그냥 결과를 보여야 할 때
        c.accept("Pineapple");
        c.accept("Strawberry");
    }
}

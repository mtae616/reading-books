import java.util.List;
import java.util.stream.Collectors;

@java.lang.FunctionalInterface
interface MyFunctionalInterface {
    void run();
}

@java.lang.FunctionalInterface
interface MySortFunctionalInterface {
    List<Integer> sort(List<Integer> list);
}

public class FunctionalInterface {

    public static void main(String[] args) {
        MyFunctionalInterface mf = () -> System.out.println("Hello World");
        mf.run();

        List<Integer> integers = List.of(5, 4, 3, 2, 1);
        MySortFunctionalInterface ms = (list) -> list.stream().sorted().collect(Collectors.toList());
        ms.sort(integers).forEach(System.out::println);

        // Predicate example
        java.util.function.Predicate<Integer> isEven = (n) -> n % 2 == 0;
        System.out.println(isEven.test(4));

        // Consumer example
        java.util.function.Consumer<Integer> print = System.out::println;
        print.accept(5);

        // Function example
        java.util.function.Function<Integer, Integer> plus10 = (n) -> n + 10;
        System.out.println(plus10.apply(5));

        // Supplier example
        java.util.function.Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());

        // BiPredicate example
        java.util.function.BiPredicate<Integer, String> biPredicate = (i, s) -> {
            return i == Integer.parseInt(s);
        };
        System.out.println(biPredicate.test(1, "1"));

        // BiConsumer example
        java.util.function.BiConsumer<Integer, String> biConsumer = (i, s) -> {
            System.out.println(i);
            System.out.println(s);
        };
        biConsumer.accept(1, "1");

        // BiFunction example
        java.util.function.BiFunction<Integer, String, String> biFunction = (i, s) -> {
            return i + s;
        };
        System.out.println(biFunction.apply(1, "1"));

    }

}

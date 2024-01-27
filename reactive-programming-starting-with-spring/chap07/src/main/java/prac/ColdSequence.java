package prac;

import reactor.core.publisher.Flux;

import java.util.Arrays;

public class ColdSequence {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> cold = Flux.fromIterable(Arrays.asList("alpha", "bravo", "charlie"))
                .map(String::toUpperCase)
                .log();

        cold.subscribe(System.out::println);

        Thread.sleep(2000);
        System.out.println("=====================================");

        cold.subscribe(System.out::println);

    }
}

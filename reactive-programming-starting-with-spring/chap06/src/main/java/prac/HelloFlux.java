package prac;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class HelloFlux {
    public static void main(String[] args) {
        Flux.just(6, 9, 13)
                .map(num -> num % 2)
                .subscribe(System.out::println);

        Flux.fromArray(new Integer[] {3, 6, 7, 9})
                .filter(num -> num > 6)
                .map(num -> num * 2)
                .subscribe(System.out::println);

        Flux<String> stringFlux = Mono.justOrEmpty("Steve")
                .concatWith(Mono.justOrEmpty("Jobs"));

        stringFlux.subscribe(System.out::println);

        Mono<List<String>> listMono = Flux.concat(
                        Flux.just("alpha", "bravo", "charlie"),
                        Flux.just("delta", "echo", "foxtrot")
                )
                .collectList();
        listMono.subscribe(System.out::println);
    }
}

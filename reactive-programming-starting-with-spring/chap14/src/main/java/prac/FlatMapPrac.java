package prac;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapPrac {
    public static void main(String[] args) {
//        Mono.just("Hello") // Mono<String>
//            .flatMap(s -> Mono.just(s + " Mono!")) // Mono<Mono<String>> -> Mono<String>
//            .subscribe(System.out::println); // Hello Mono!

        List<List<String>> list1 = Arrays.asList(List.of("a", "b", "c"), List.of("d", "e", "f"));

        list1.stream().flatMap(data -> {
            data.forEach(System.out::println);
            System.out.println("===");
            return data.stream();
        }).collect(Collectors.toList());

    }
}

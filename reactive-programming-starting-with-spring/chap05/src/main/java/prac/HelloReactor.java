package prac;

import reactor.core.publisher.Flux;

public class HelloReactor {
    public static void main(String[] args) {
        Flux.just("Hello", "Reactor!") // data source
            .map(data -> data.toLowerCase()) // operator -> return Flux
            .subscribe(System.out::println);
    }
}

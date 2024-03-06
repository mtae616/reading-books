package prac;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

public class HelloFlux {
    public static void main(String[] args) {
//        Flux.just(6, 9, 13)
//                .map(num -> num % 2) // 2로 나눈 나머지 값으로 변환
//                .subscribe(System.out::println);
//
//        Flux.fromArray(new Integer[] {3, 6, 7, 9})
//                .filter(num -> num > 6) // 6보다 큰 값만 통과
//                .map(num -> num * 2) // 2를 곱함
//                .subscribe(System.out::println);
//
//        Flux<String> stringFlux = Mono.justOrEmpty("Steve") // Mono 로 데이터 1개를 emit
//                .concatWith(Mono.justOrEmpty("Jobs")); // 다른 Mono 를 연결하여 데이터를 emit
//
//        stringFlux.subscribe(System.out::println); // Steve, Jobs
//
//        Mono<List<String>> listMono = Flux.concat(
//                        Flux.just("alpha", "bravo", "charlie"),
//                        Flux.just("delta", "echo", "foxtrot")
//                )
//                .collectList(); // Flux 에서 emit 된 데이터를 List 로 변환
//        listMono.subscribe(System.out::println); // [alpha, bravo, charlie, delta, echo, foxtrot]

        Mono.zip( // 두 개의 Mono 를 결합하여 Tuple2 를 emit
                Mono.just(true),
                Mono.just("Hello")
        )
        .doOnNext(data -> { // onNext ignal 을 emit 할 때 호출
            Tuple2<Boolean, String> now = data;
            Boolean t1 = now.getT1();
            String t2 = now.getT2();
            System.out.println("t1 = " + t1);
            System.out.println("t2 = " + t2);
        })
        .subscribe();
    }
}

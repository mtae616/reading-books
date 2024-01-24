package spring.in.action.chap10;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FluxBuffer {

    public static void main(String[] args) {
        // buffer 를 기준으로 flux 를 나눈다.
        // 아래에서는 [apple, banana, grape], [kiwi, mango] 로 나누어진다.
//        Flux.just("apple", "banana", "grape", "kiwi", "mango")
//            .buffer(3)
//            .subscribe(System.out::println);


        // x 는 buffer 에서 나누어진 flux 들이다.
        // [apple, banana, grape], [kiwi, mango] 를 각각 대문자로 바꾸어 출력한다.
        // 이 때 subscribeOn 을 사용하면, 각각의 flux 를 별도의 스레드에서 병렬 실행할 수 있다.
//        Flux.just("apple", "banana", "grape", "kiwi", "mango")
//                .buffer(3)
//                .flatMap(x ->
//                            Flux.fromIterable(x)
//                                    .map(y -> y.toUpperCase())
//                                    .subscribeOn(Schedulers.parallel())
//                                    .log()
//                )
//                .subscribe(System.out::println);

        // collectMap 은 flux 의 항목을 key 와 value 로 나누어 map 을 만든다.
        Mono<Map<Character, String>> mapMono = Flux.just("apple", "banana", "grape", "kiwi", "mango")
                .collectMap(a -> a.charAt(0));
//        mapMono.subscribe(System.out::println);

        // all 은 flux 의 모든 항목이 조건을 만족하는지 확인한다.
        // any 는 flux 의 항목 중 하나라도 조건을 만족하는지 확인한다.
        Mono<Boolean> all = Flux.just("apple", "banana", "grape", "kiwi", "mango")
//                .all(a -> a.length() > 4);
                    .any(a -> a.length() > 4);
        all.subscribe(System.out::println);


        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            es.shutdown();
        });
    }
}

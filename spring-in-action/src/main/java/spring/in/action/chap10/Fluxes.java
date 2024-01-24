package spring.in.action.chap10;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fluxes {
    public static void main(String[] args) {
        // 구독자가 없음, 데이터는 전달되지 않음, 구독한다는 것은 데이터가 흘러갈 수 있게 하는 것
        Flux<String> flux = Flux.just("alpha", "bravo", "charlie");

        // subscribe 에 지정된 람다는 java.util.Consumer 이며, Consumer 는 reactive stream 의 Subscriber 와 같은 역할을 한다.
        // flux 를 test 할 때에는 Stepverifier 를 사용한다, test file 참고
        flux.subscribe(System.out::println);

        System.out.println("=====================================");

        // array 로부터 flux 를 만듬
        String[] flux2 = {"alpha", "bravo", "charlie"};
        Flux<String> fromFlux = Flux.fromArray(flux2);
        fromFlux.subscribe(System.out::println);

        System.out.println("=====================================");

        // iterable 로부터 flux 을 만듬
        ArrayList<String> fluxArray = new ArrayList<>();
        fluxArray.add("alpha");
        fluxArray.add("bravo");
        fluxArray.add("charlie");
        Flux<String> fromIterable = Flux.fromIterable(fluxArray);
        fromIterable.subscribe(System.out::println);

        System.out.println("=====================================");

        // range 로부터 flux 를 만듬
        Flux<Integer> rangeFlux = Flux.range(1, 5);
        rangeFlux.subscribe(System.out::println);

        System.out.println("=====================================");

        // interval 을 두고 0 < n 으로 flux 를 만듬
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1)).take(5);
        intervalFlux.subscribe(System.out::println);

        ExecutorService es = Executors.newSingleThreadExecutor();

        es.execute(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            es.shutdown();
        });

    }
}

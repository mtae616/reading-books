package spring.in.action.chap10;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConvertFluxes {
    public static void main(String[] args) {

        // skip 은 flux 에서 지정된 수의 항목을 건너뛰고 나머지 항목을 방출한다.
//        Flux.just("alpha", "bravo", "charlie")
//            .skip(1)
//            .subscribe(System.out::println);

        // skip 에는 시간을 지정할 수도 있다.
//        Flux.just("alpha", "bravo", "charlie")
//            .delayElements(Duration.ofSeconds(1))
//            .skip(Duration.ofSeconds(2))
//            .subscribe(System.out::println);

        // skip 의 반대로 데이터를 1개만 취한다
//        Flux.just("alpha", "bravo", "charlie")
//            .take(1)
//            .subscribe(System.out::println);

        // 마찬가지로 시간을 지정할 수 있다
//        Flux.just("alpha", "bravo", "charlie")
//                .delayElements(Duration.ofSeconds(1))
//                .take(Duration.ofMillis(2500))
//                .subscribe(System.out::println);

        Flux.just("alpha", "bravo", "charlie")
                .filter(s -> !s.contains("ph"))
                .log()
                .subscribe(System.out::println);

        System.out.println("=====================================");

        Flux.just("alpha", "bravo", "charlie", "alpha")
                .distinct()
                .log()
                .subscribe(System.out::println);

        System.out.println("=====================================");

        Flux.just("alpha", "bravo", "charlie", "alpha")
                .map(String::length)
                .subscribeOn(Schedulers.parallel()) // parallel 로 실행, ForkJoinPool 을 사용
                .log()
                .subscribe(System.out::println);

        // Schedulers 의 동시성 모델
        // .immediate() : 현재 스레드에서 실행
        // .single() : 단일 스레드에서 실행, 스레드는 재사용이 가능하다
        // .newSingle() : 단일 스레드에서 실행, 스레드는 재사용이 불가능하다
        // .elastic() : 스레드 풀에서 실행, 스레드는 재사용이 가능하다, idle thread 는 제거된다.

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

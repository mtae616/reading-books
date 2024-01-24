package spring.in.action.chap10;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeFluxes {
    public static void main(String[] args) {
        // delayElements 를 사용하면, 각각의 flux 에 대해 지정된 시간만큼의 delay 가 생긴다. 아래에서는 alpha, bravo, charlie 각각에 대해 500ms 의 delay 가 생긴다.
        // delaySubscription 을 사용하면, flux 의 구독이 시작되는 시간을 지정할 수 있다.
        Flux<String> stringFlux1 = Flux.just("alpha", "bravo", "charlie")
                .delayElements(Duration.ofMillis(500));

        Flux<String> stringFlux2 = Flux.just("delta", "echo", "foxtrot")
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));

        // 실제 출력은 alpha, delta, bravo, echo, charlie, foxtrot 순으로 출력된다.
        // mergeWith 에 방출되는 항목의 순서는 두 개의 소스 Flux 로부터 방출되는 시간에 맞춰 결정된다
//        stringFlux1.mergeWith(stringFlux2)
//                .subscribe(System.out::println);

        System.out.println("=====================================");

        // zip 은 두 개의 flux 로부터 방출되는 항목을 조합하여 새로운 flux 를 만든다.
        // 정적인 생성 오퍼레이션으로 zip 을 사용할 때에는, 두 개의 flux 가 동시에 시작되고, 각각의 flux 에서 방출되는 항목의 수가 같아야 한다.
        // 출력은 [alpha delta], [bravo echo], [charlie foxtrot] 순으로 출력된다.
        Flux.zip(stringFlux1, stringFlux2)
                .subscribe(System.out::println);

        System.out.println("=====================================");

        Flux.zip(stringFlux1, stringFlux2, (s1, s2) -> String.format("%s-%s", s1, s2))
                .subscribe(System.out::println);



        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            es.shutdown();
        });

    }
}

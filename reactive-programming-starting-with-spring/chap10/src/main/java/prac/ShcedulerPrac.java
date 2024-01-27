package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ShcedulerPrac {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9})
                // subscribeOn 은 구독이 발생한 직후 실행될 스레드를 지정
                // 원본 Publisher 의 동작을 처리하기 위한 스레드를 할당
//                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe"))
                // publishOn 은 Downstream 으로 Signal 을 전송할 때 실행되는 스레드를 제어하는 역할을 하는 Operator
                // publishOn 기준으로 아래쪽인 Downstream 의 실행 스레드를 변경
                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));



        // parallel() Operator 는 병렬성을 가지는 물리적인 스레드, RR 방식으로 논리적인 코어(물리적인 스레드)를 할당, 4코어 8스레드 CPU 라면 총 8개의 스레드를 병렬로 실행
        Flux.fromArray(new Integer[] {1, 3, 5, 7, 9})
                .parallel() // 논리적인 코어(물리적인 스레드) 수에 맞게 사전에 골고루 분배하는 역할만
                .runOn(Schedulers.parallel()) // 실제로 병렬 작업을 수행할 스레드의 할당은 runOn
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }
}

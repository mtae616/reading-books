package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class AndPrac {
    public static void main(String[] args) throws InterruptedException {
        Mono.just("Task 1")
                .delayElement(Duration.ofSeconds(1))
                .doOnNext(data -> log.info(" Mono doOnNext: {}", data))
                .and(
                        Flux.just("Task 2", "Task 3")
                                .delayElements(Duration.ofMillis(600L))
                                .doOnNext(data -> log.info(" Flux doOnNext: {}", data))
                ).subscribe(
                        data -> log.info(" onNext: {}", data), // 각 sequence 가 종료되어도 emit 되는 데이터가 없음
                        error -> log.error(" onError: {}", error.getMessage()),
                        () -> log.info(" onComplete") // onComplete signal 만 전송
                );

        Thread.sleep(5000);
    }
}

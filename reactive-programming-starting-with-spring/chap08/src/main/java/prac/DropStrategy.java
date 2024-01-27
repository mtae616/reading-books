package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class DropStrategy {
    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureDrop() // DROP 전략 사용
                .doOnNext(data -> log.info("# doOnNext: " + data)) // Publisher 에서 데이터를 전달받을 때마다 로그 출력
                .publishOn(Schedulers.parallel()) // Scheduler 를 지정
                .subscribe(
                        data -> {
                            try {
                                Thread.sleep(5L);
                            } catch (InterruptedException e) { }
                            log.info("# onNext: " + data);
                        },
                        error -> log.info("# error: " + error)
                );

        Thread.sleep(2000L);
    }
}

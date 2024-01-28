package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class BiFunctionZip {
    public static void main(String[] args) throws InterruptedException {
        Flux.zip(
                Flux.just(1, 2, 3).delayElements(Duration.ofMillis(300L)),
                Flux.just(4, 5, 6).delayElements(Duration.ofMillis(300L)),
                (a, b) -> a * b // BiFunction 으로 두개의 Flux가 emit 하는 데이터를 조합 후 Subscriber 에게 전달
        ).subscribe(data -> log.info("data: {}", data));

        Thread.sleep(3000L);    }
}

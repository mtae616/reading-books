package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
public class DeferPrac {
    public static void main(String[] args) throws InterruptedException {
        log.info(" # start {}", LocalDateTime.now());

        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        Thread.sleep(2000L);

        justMono.subscribe(t -> log.info("justMono: {}", t));
        deferMono.subscribe(t -> log.info("deferMono: {}", t));
//        12:17:57.881 [main] INFO prac.DeferPrac -- justMono: 2024-01-28T12:17:55.833663
//        12:17:57.881 [main] INFO prac.DeferPrac -- deferMono: 2024-01-28T12:17:57.881499

        Thread.sleep(2000L);

        justMono.subscribe(t -> log.info("justMono: {}", t));
        deferMono.subscribe(t -> log.info("deferMono: {}", t));
//        12:17:59.885 [main] INFO prac.DeferPrac -- justMono: 2024-01-28T12:17:55.833663
//        12:17:59.886 [main] INFO prac.DeferPrac -- deferMono: 2024-01-28T12:17:59.886306
    }
}

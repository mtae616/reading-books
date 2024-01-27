package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class DropOldestStrategy {

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(300L))
                .doOnNext(data -> log.info("# emitted by original Flux: {}", data))
                .onBackpressureBuffer(2,
                        dropped -> log.info("# dropped: {}", dropped),
                        BufferOverflowStrategy.DROP_OLDEST)
                .doOnNext(data -> log.info("# emitted by buffered Flux: {}", data))
                .publishOn(Schedulers.parallel(), false, 1)
                .subscribe(
                        data -> {
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                            }
                            log.info("# onNext: {}", data);
                        },
                        error -> log.error("# error: {}", error)
                );

        Thread.sleep(3000L);
    }

}

//18:20:33.783 [parallel-2] INFO prac.DropOldestStrategy -- # emitted by original Flux: 0
//18:20:33.787 [parallel-2] INFO prac.DropOldestStrategy -- # emitted by buffered Flux: 0
//18:20:34.081 [parallel-2] INFO prac.DropOldestStrategy -- # emitted by original Flux: 1
//18:20:34.385 [parallel-2] INFO prac.DropOldestStrategy -- # emitted by original Flux: 2
//18:20:34.685 [parallel-2] INFO prac.DropOldestStrategy -- # emitted by original Flux: 3
//18:20:34.687 [parallel-2] INFO prac.DropOldestStrategy -- # dropped: 1
//18:20:34.793 [parallel-1] INFO prac.DropOldestStrategy -- # onNext: 0
//18:20:34.793 [parallel-1] INFO prac.DropOldestStrategy -- # emitted by buffered Flux: 2
//18:20:34.984 [parallel-2] INFO prac.DropOldestStrategy -- # emitted by original Flux: 4
//18:20:35.285 [parallel-2] INFO prac.DropOldestStrategy -- # emitted by original Flux: 5
//18:20:35.286 [parallel-2] INFO prac.DropOldestStrategy -- # dropped: 3
//18:20:35.584 [parallel-2] INFO prac.DropOldestStrategy -- # emitted by original Flux: 6
//18:20:35.584 [parallel-2] INFO prac.DropOldestStrategy -- # dropped: 4
//18:20:35.794 [parallel-1] INFO prac.DropOldestStrategy -- # onNext: 2
//18:20:35.795 [parallel-1] INFO prac.DropOldestStrategy -- # emitted by buffered Flux: 5
//18:20:35.881 [parallel-2] INFO prac.DropOldestStrategy -- # emitted by original Flux: 7
//18:20:36.181 [parallel-2] INFO prac.DropOldestStrategy -- # emitted by original Flux: 8
//18:20:36.181 [parallel-2] INFO prac.DropOldestStrategy -- # dropped: 6
//18:20:36.481 [parallel-2] INFO prac.DropOldestStrategy -- # emitted by original Flux: 9
//18:20:36.481 [parallel-2] INFO prac.DropOldestStrategy -- # dropped: 7

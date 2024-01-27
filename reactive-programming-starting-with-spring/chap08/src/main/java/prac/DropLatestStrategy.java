package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class DropLatestStrategy {

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(300L))
                .doOnNext(data -> log.info("# emitted by original Flux: {}", data))
                .onBackpressureBuffer(2,
                        dropped -> log.info("# dropped: {}", dropped),
                        BufferOverflowStrategy.DROP_LATEST)
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

//        18:18:02.259 [parallel-2] INFO prac.DropLatestStrategy -- # emitted by original Flux: 0
//        18:18:02.263 [parallel-2] INFO prac.DropLatestStrategy -- # emitted by buffered Flux: 0
//        18:18:02.561 [parallel-2] INFO prac.DropLatestStrategy -- # emitted by original Flux: 1
//        18:18:02.858 [parallel-2] INFO prac.DropLatestStrategy -- # emitted by original Flux: 2
//        18:18:03.159 [parallel-2] INFO prac.DropLatestStrategy -- # emitted by original Flux: 3
//        18:18:03.160 [parallel-2] INFO prac.DropLatestStrategy -- # dropped: 3
//        18:18:03.266 [parallel-1] INFO prac.DropLatestStrategy -- # onNext: 0
//        18:18:03.267 [parallel-1] INFO prac.DropLatestStrategy -- # emitted by buffered Flux: 1
//        18:18:03.458 [parallel-2] INFO prac.DropLatestStrategy -- # emitted by original Flux: 4
//        18:18:03.757 [parallel-2] INFO prac.DropLatestStrategy -- # emitted by original Flux: 5
//        18:18:03.757 [parallel-2] INFO prac.DropLatestStrategy -- # dropped: 5
//        18:18:04.061 [parallel-2] INFO prac.DropLatestStrategy -- # emitted by original Flux: 6
//        18:18:04.061 [parallel-2] INFO prac.DropLatestStrategy -- # dropped: 6
//        18:18:04.271 [parallel-1] INFO prac.DropLatestStrategy -- # onNext: 1
//        18:18:04.272 [parallel-1] INFO prac.DropLatestStrategy -- # emitted by buffered Flux: 2
//        18:18:04.357 [parallel-2] INFO prac.DropLatestStrategy -- # emitted by original Flux: 7
//        18:18:04.661 [parallel-2] INFO prac.DropLatestStrategy -- # emitted by original Flux: 8
//        18:18:04.661 [parallel-2] INFO prac.DropLatestStrategy -- # dropped: 8
//        18:18:04.959 [parallel-2] INFO prac.DropLatestStrategy -- # emitted by original Flux: 9
//        18:18:04.959 [parallel-2] INFO prac.DropLatestStrategy -- # dropped: 9
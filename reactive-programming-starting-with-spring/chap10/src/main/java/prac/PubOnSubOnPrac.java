package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class PubOnSubOnPrac {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .subscribeOn(Schedulers.boundedElastic())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# doOnNext filtered: {}", data))
                .publishOn(Schedulers.parallel())
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# doOnNext mapped: {}", data))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);


//        21:48:32.620 [boundedElastic-1] INFO prac.PubOnSubOnPrac -- # doOnNext: 1
//        21:48:32.622 [boundedElastic-1] INFO prac.PubOnSubOnPrac -- # doOnNext: 3
//        21:48:32.622 [boundedElastic-1] INFO prac.PubOnSubOnPrac -- # doOnNext: 5
//        21:48:32.622 [boundedElastic-1] INFO prac.PubOnSubOnPrac -- # doOnNext filtered: 5
//        21:48:32.622 [boundedElastic-1] INFO prac.PubOnSubOnPrac -- # doOnNext: 7
//        21:48:32.622 [boundedElastic-1] INFO prac.PubOnSubOnPrac -- # doOnNext filtered: 7
//        21:48:32.622 [parallel-1] INFO prac.PubOnSubOnPrac -- # doOnNext mapped: 50
//        21:48:32.622 [parallel-1] INFO prac.PubOnSubOnPrac -- # onNext: 50
//        21:48:32.622 [parallel-1] INFO prac.PubOnSubOnPrac -- # doOnNext mapped: 70
//        21:48:32.623 [parallel-1] INFO prac.PubOnSubOnPrac -- # onNext: 70
    }
}

package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class PubOnSubOnPrac {
    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .doOnNext(data -> log.info("# doOnNext: {}", data)) // boundedElastic-1
                .subscribeOn(Schedulers.boundedElastic()) // 구독이 발생한 직후 실행될 스레드를 지정
                .filter(data -> data > 3) // boundedElastic-1, 따로 지정하지 않으면 위의 subscribeOn 스레드에서 실행
                .doOnNext(data -> log.info("# doOnNext filtered: {}", data))
                .publishOn(Schedulers.parallel())
                .map(data -> data * 10) // 위에서 parallel 로 스레드를 변경했기 때문에 parallel-1
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

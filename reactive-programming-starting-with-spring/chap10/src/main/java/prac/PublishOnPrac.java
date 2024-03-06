package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class PublishOnPrac {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .publishOn(Schedulers.parallel()) // 이후 연산을 수행할 스레드를 지정
                .filter(data -> data > 3) // parallel-2
                .doOnNext(data -> log.info("# doOnNext filtered: {}", data))
                .publishOn(Schedulers.parallel()) // 이후 연산을 수행할 스레드를 지정
                .map(data -> data * 10) // parallel-1
                .doOnNext(data -> log.info("# doOnNext mapped: {}", data))
                .subscribe(data -> log.info("# onNext: {}", data));

//        21:46:07.260 [main] INFO prac.PublishOnPrac -- # doOnNext: 1
//        21:46:07.262 [main] INFO prac.PublishOnPrac -- # doOnNext: 3
//        21:46:07.262 [main] INFO prac.PublishOnPrac -- # doOnNext: 5
//        21:46:07.262 [main] INFO prac.PublishOnPrac -- # doOnNext: 7
//        21:46:07.262 [parallel-2] INFO prac.PublishOnPrac -- # doOnNext filtered: 5
//        21:46:07.262 [parallel-2] INFO prac.PublishOnPrac -- # doOnNext filtered: 7
//        21:46:07.262 [parallel-1] INFO prac.PublishOnPrac -- # doOnNext mapped: 50
//        21:46:07.262 [parallel-1] INFO prac.PublishOnPrac -- # onNext: 50
//        21:46:07.262 [parallel-1] INFO prac.PublishOnPrac -- # doOnNext mapped: 70
//        21:46:07.262 [parallel-1] INFO prac.PublishOnPrac -- # onNext: 70
    }
}

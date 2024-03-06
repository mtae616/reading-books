package prac;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class doOnPrac {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .doFinally(signalType -> log.info("# doFinally 1: {}", signalType)) // onComplete signal 이후에 호출
                .doFinally(signalType -> log.info("# doFinally 2: {}", signalType))
                .doOnNext(data -> log.info("# range > doOnNext(): {}", data)) // onNext signal 이후에 호출
                .doOnRequest(data -> log.info("# doOnRequest: {}", data)) // Subscription.request() 호출 시 호출
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe 1")) // Subscription 이 생성되어서 subscribe() 호출 시 호출
                .doFirst(() -> log.info("# doFirst()")) // 첫 번째로 호출
                .filter(num -> num % 2 == 1)
                .doOnNext(data -> log.info("# filter > doOnNext(): {}", data)) // onNext signal 이후에 호출
                .doOnComplete(() -> log.info("# doOnComplete()")) // onComplete signal 이후에 호출
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        log.info("# hookOnNext: {}", value);
                        request(1);
                    }
                });
    }
}

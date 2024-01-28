package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class PublishPrac {
    public static void main(String[] args) throws InterruptedException {
//        ConnectableFlux<Integer> publish = Flux.range(1, 5)
//                .delayElements(Duration.ofMillis(300L))
//                .publish();

        Flux<Integer> publish = Flux.range(1, 5)
                .delayElements(Duration.ofMillis(300L))
                .publish().autoConnect(1)
                .publish().refCount(1);

        Thread.sleep(500L);
        publish.subscribe(data -> log.info("Subscriber #1: {}", data));

        Thread.sleep(500L);
        publish.subscribe(data -> log.info("Subscriber #2: {}", data));

//        publish.connect();

        Thread.sleep(1000);
        Disposable subscribe = publish.subscribe(data -> log.info("Subscriber #3: {}", data));
        subscribe.dispose();

        Thread.sleep(2000);
    }
}

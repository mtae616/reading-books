package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class SinksMany {
    public static void main(String[] args) {
//        Sinks.Many<Integer> unicast = Sinks.many().unicast().onBackpressureBuffer();
//        Flux<Integer> fluxView = unicast.asFlux();
//
//        unicast.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
//        unicast.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
//
//        fluxView.subscribe(data -> log.info(" # Subscriber1 : {}", data));
//
//        unicast.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        // Subscriber2 는 UniCast 이기 때문에 에러 발생
        // Caused by: java.lang.IllegalStateException: Sinks.many().unicast() sinks only allow a single Subscriber
//        fluxView.subscribe(data -> log.info(" # Subscriber2 : {}", data));


//        Sinks.Many<Object> multicast = Sinks.many().multicast().onBackpressureBuffer();
//        Flux<Object> fluxMulti = multicast.asFlux();
//        multicast.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
//        multicast.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
//
//        fluxMulti.subscribe(data -> log.info(" # Subscriber1 : {}", data));
//        fluxMulti.subscribe(data -> log.info(" # Subscriber2 : {}", data));
//
//        multicast.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
        // => 2번째 subscribe 는 3만 받음, Sinks 가 Publisher 의 역할을 할 경우 Hot Publisher 가 됨
        // onBackpressureBuffer 는 warm up 특징을 가지는 hot sequence
//        21:12:05.016 [main] INFO prac.SinksMany --  # Subscriber1 : 1
//        21:12:05.018 [main] INFO prac.SinksMany --  # Subscriber1 : 2
//        21:12:05.019 [main] INFO prac.SinksMany --  # Subscriber1 : 3
//        21:12:05.019 [main] INFO prac.SinksMany --  # Subscriber2 : 3



        // emit 된 데이터 중 2개만 뒤로 돌려서 전달
        Sinks.Many<Object> replay = Sinks.many().replay().limit(2);
        Flux<Object> fluxReplay = replay.asFlux();

        replay.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        replay.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        replay.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxReplay.subscribe(data -> log.info(" # Subscriber1 : {}", data));
        replay.emitNext(4, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxReplay.subscribe(data -> log.info(" # Subscriber2 : {}", data));
//        21:15:56.760 [main] INFO prac.SinksMany --  # Subscriber1 : 2
//        21:15:56.762 [main] INFO prac.SinksMany --  # Subscriber1 : 3
//        21:15:56.762 [main] INFO prac.SinksMany --  # Subscriber1 : 4
//        21:15:56.762 [main] INFO prac.SinksMany --  # Subscriber2 : 3
//        21:15:56.762 [main] INFO prac.SinksMany --  # Subscriber2 : 4
    }
}

package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

import static prac.CreateOperator.doTask;

@Slf4j
public class SinkPrac {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;
        // Sinks 는 CreateOperator 에서 실행했던 doTask 를 Multi Threading 해줌
        // Sinks 는 프로그래밍 방식으로 Signal 을 전송할 수 있고, Thread safe 함
        // -> Sinks 의 경우 동시 접근을 감지하고 동시 접근하는 스레드 중 하나가 빠르게 실패함으로써 스레드 안정성을 보장함
        Sinks.Many<Object> unicast = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> fluxView = unicast.asFlux();

        IntStream
                .range(1, tasks)
                .forEach(n -> {
                    try {
                        new Thread(() -> {
                            unicast.emitNext(doTask(n), Sinks.EmitFailureHandler.FAIL_FAST);
                            log.info(" # emitted : {}", n);
                        }).start();
                        Thread.sleep(100L);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        fluxView.publishOn(Schedulers.parallel())
                .map(result -> result + " success!!")
                .doOnNext(n -> log.info(" # map() : {}", n))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info(" # onNext() : {}", data));
        Thread.sleep(2000L);
    }
}

//20:56:51.740 [Thread-0] INFO prac.SinkPrac --  # emitted : 1
//20:56:51.842 [Thread-1] INFO prac.SinkPrac --  # emitted : 2
//20:56:51.947 [Thread-2] INFO prac.SinkPrac --  # emitted : 3
//20:56:52.052 [Thread-3] INFO prac.SinkPrac --  # emitted : 4
//20:56:52.153 [Thread-4] INFO prac.SinkPrac --  # emitted : 5
//20:56:52.310 [parallel-2] INFO prac.SinkPrac --  # map() : task1 result success!!
//20:56:52.311 [parallel-2] INFO prac.SinkPrac --  # map() : task2 result success!!
//20:56:52.311 [parallel-1] INFO prac.SinkPrac --  # onNext() : task1 result success!!
//20:56:52.311 [parallel-2] INFO prac.SinkPrac --  # map() : task3 result success!!
//20:56:52.311 [parallel-1] INFO prac.SinkPrac --  # onNext() : task2 result success!!
//20:56:52.311 [parallel-2] INFO prac.SinkPrac --  # map() : task4 result success!!
//20:56:52.311 [parallel-1] INFO prac.SinkPrac --  # onNext() : task3 result success!!
//20:56:52.311 [parallel-1] INFO prac.SinkPrac --  # onNext() : task4 result success!!
//20:56:52.311 [parallel-2] INFO prac.SinkPrac --  # map() : task5 result success!!
//20:56:52.311 [parallel-1] INFO prac.SinkPrac --  # onNext() : task5 result success!!
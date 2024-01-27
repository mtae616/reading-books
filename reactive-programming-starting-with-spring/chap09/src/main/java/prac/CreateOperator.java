package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

@Slf4j
public class CreateOperator {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;

        // Flux 생성, FluxSink를 이용해 데이터를 push
        Flux.create((FluxSink<String> sink) -> {
            // 1 ~ 6까지의 숫자를 생성
            IntStream.range(1, tasks)
                    .forEach(n -> sink.next(doTask(n))); // 데이터를 push
        })
        .subscribeOn(Schedulers.boundedElastic()) // 데이터를 발행하는 스레드를 boundedElastic 실행 -> Operator가 실행되는 스레드를 변경
        .doOnNext(n -> log.info(" # create() : {}", n)) // onNext() 실행 전에 로그 출력
        .publishOn(Schedulers.parallel()) // 다음에 오는 Operator 실행 스레드를 parallel로 변경
        .map(result -> result + " success!!")
        .doOnNext(n -> log.info(" # map() : {}", n))
        .publishOn(Schedulers.parallel()) // 아래 subscriber 에게 전달하는 스레드를 parallel로 변경
        .subscribe(data -> log.info(" # onNext() : {}", data));

        Thread.sleep(500L);
    }

    static String doTask(int taskNumber) {
        return "task" + taskNumber + " result";
    }
}

//20:43:18.862 [boundedElastic-1] INFO prac.CreateOperator --  # create() : task1 result
//20:43:18.864 [boundedElastic-1] INFO prac.CreateOperator --  # create() : task2 result
//20:43:18.864 [boundedElastic-1] INFO prac.CreateOperator --  # create() : task3 result
//20:43:18.864 [boundedElastic-1] INFO prac.CreateOperator --  # create() : task4 result
//20:43:18.864 [boundedElastic-1] INFO prac.CreateOperator --  # create() : task5 result
//20:43:18.864 [parallel-2] INFO prac.CreateOperator --  # map() : task1 result success!!
//20:43:18.864 [parallel-2] INFO prac.CreateOperator --  # map() : task2 result success!!
//20:43:18.864 [parallel-2] INFO prac.CreateOperator --  # map() : task3 result success!!
//20:43:18.864 [parallel-2] INFO prac.CreateOperator --  # map() : task4 result success!!
//20:43:18.864 [parallel-1] INFO prac.CreateOperator --  # onNext() : task1 result success!!
//20:43:18.864 [parallel-2] INFO prac.CreateOperator --  # map() : task5 result success!!
//20:43:18.864 [parallel-1] INFO prac.CreateOperator --  # onNext() : task2 result success!!
//20:43:18.864 [parallel-1] INFO prac.CreateOperator --  # onNext() : task3 result success!!
//20:43:18.864 [parallel-1] INFO prac.CreateOperator --  # onNext() : task4 result success!!
//20:43:18.864 [parallel-1] INFO prac.CreateOperator --  # onNext() : task5 result success!!
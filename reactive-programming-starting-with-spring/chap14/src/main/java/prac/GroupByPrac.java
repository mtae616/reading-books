package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class GroupByPrac {
    public static void main(String[] args) throws InterruptedException {
        Integer[] numbers = {1, 2, 3, 4, 5};

        Flux.fromArray(numbers)
                .groupBy(num -> num % 2 == 0 ? "EVEN" : "ODD")
                .flatMap(Flux::collectList)
                .subscribe(dat -> log.info("dat: {}", dat));

        Thread.sleep(1000);
    }
}

package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.logging.Level;

@Slf4j
public class CheckpointPrac {
    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint("zipwith", true)
                .map(num -> num + 2)
                .checkpoint()
                .log("Fruit", Level.WARNING) // check point 는 log 가 있어야 뜨는듯, 책이랑 뭔가 버전이 바뀐듯
                .subscribe(
                        data -> log.info(" onNext: {}", data),
                        error -> log.error(" onError: {}", error.getMessage())
                );
    }
}

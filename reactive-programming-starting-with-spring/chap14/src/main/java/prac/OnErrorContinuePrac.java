package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class OnErrorContinuePrac {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .map(num -> {
                    if (num == 3) {
                        throw new RuntimeException("Exception Occurred");
                    }
                    return num;
                })
                .onErrorContinue((error, data) -> {
                    log.error("Exception Occurred: {}, {}", data, error.getMessage());
                })
                .subscribe(data -> log.info("onErrorContinue: {}", data));
    }
}

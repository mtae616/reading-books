package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class OnErrorResumePrac {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .map(num -> {
                    if (num == 3) {
                        throw new RuntimeException("Exception Occurred");
                    }
                    return num;
                })
                .onErrorResume(error -> {
                    log.error("Exception Occurred: {}", error.getMessage());
                    return Flux.range(11, 15);
                })
                .subscribe(data -> log.info("onErrorResume: {}", data));
    }
}

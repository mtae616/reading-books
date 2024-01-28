package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class OnErrorReturnPrac {

    public static void main(String[] args) {
        Flux.range(1, 5)
                .map(num -> {
                    if (num == 3) {
                        throw new RuntimeException("Exception Occurred");
                    }
                    return num;
                })
                .onErrorReturn(RuntimeException.class, 0)
                .subscribe(data -> log.info("onErrorReturn: {}", data));
    }
}

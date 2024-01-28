package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

@Slf4j
public class GeneratePrac {
    public static void main(String[] args) {
        Flux.generate(() -> 0, (state, sink) -> {
                    sink.next(state);
                    if (state == 10) {
                        sink.complete();
                    }
                    return ++state;
                })
                .subscribe(data -> log.info("data: {}", data));

        System.out.println("=====================================");

        final int dan = 3;
        Flux.generate(() -> Tuples.of(dan, 1), (state, sink) -> {
                    sink.next(state.getT1() + " * " + state.getT2() + " = " + (state.getT1() * state.getT2()));
                    if (state.getT2() == 9) {
                        sink.complete();
                    }
                    return Tuples.of(state.getT1(), state.getT2() + 1);
                })
                .subscribe(data -> log.info("data: {}", data));
    }
}

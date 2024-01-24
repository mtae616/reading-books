package spring.in.action.chap10;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FluxesTest {

    @Test
    void testFluxes() {
        Flux<String> flux = Flux.just("alpha", "bravo", "charlie");

        StepVerifier.create(flux)
            .expectNext("alpha")
            .expectNext("bravo")
            .expectNext("charlie")
            .verifyComplete();
    }

    @Test
    void fromArray() {
        String[] flux2 = {"alpha", "bravo", "charlie"};
        Flux<String> fromFlux = Flux.fromArray(flux2);

        StepVerifier.create(fromFlux)
            .expectNext("alpha")
            .expectNext("bravo")
            .expectNext("charlie")
            .verifyComplete();
    }

}
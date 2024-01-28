package prac;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.test.scheduler.VirtualTimeScheduler;

import java.time.Duration;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StepVerifierTest {
    @Test
    public void sayHelloReactorTest() {
        StepVerifier.create(Mono.just("Hello Reactor")) // sequence 생성
                .expectSubscription()
                .expectNext("Hello Reactor") // sequence 에서 예상되는 signal 기댓값 평가
                .expectNextCount(0)
//                .expectNoEvent(Duration.ZERO)
                .expectComplete()
//                .expectAccessibleContext()
//                .expectError()
                .verify(); // trigger
    }

    @Test
    public void sayHelloTest() {
        StepVerifier.create(GeneralTestExample.sayHello())
                .expectSubscription()
                .as("# check subscription")
                .expectNext("Hi")
                .as("# check hi")
                .expectNext("Reactor")
                .as("# check reactor")
                .expectComplete()
                .verify();
    }

    @Test
    public void divideByTwoTest() {
        StepVerifier.create(GeneralTestExample.divideByTwo(Flux.just(2, 4, 6, 8, 10)))
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectError(ArithmeticException.class)
                .verify();
    }

    @Test
    public void takeNumberTest() {
        Flux<Integer> source = Flux.range(0, 1000);
        StepVerifier
                .create(
                    GeneralTestExample.takeNumber(source, 500),
                    StepVerifierOptions.create().scenarioName("Verify from 0 to 499"))
                .expectSubscription()
                .expectNext(0)
                .expectNextCount(498)
                .expectNext(500)
                .expectComplete()
                .verify();
    }

    @Test
    public void getCOVID19CountTest() {
        StepVerifier
                .withVirtualTime(() -> TimeBasedTestExample.getCOVID19Count(
                                Flux.interval(Duration.ofHours(1)).take(1)
                        )
                )
                .expectSubscription()
                .then(() -> VirtualTimeScheduler
                        .get()
                        .advanceTimeBy(Duration.ofHours(1)))
                .expectNextCount(11)
                .expectComplete()
                .verify();

    }

    @Test
    public void getCOVIDCountTest() {
        StepVerifier
                .create(TimeBasedTestExample.getCOVID19Count(
                                Flux.interval(Duration.ofHours(1)).take(1)
                        )
                ).expectSubscription()
                .expectNextCount(11)
                .expectComplete()
                .verify(Duration.ofSeconds(3));
    }

    @Test
    public void generateNumberTest() {
        StepVerifier
                // create 에서 데이터 요청 갯수를 지정 -> 1
                .create(BackpressureTestExample.generateNumber(), 1L)
                .thenConsumeWhile(num -> num >= 1)
                .verifyComplete();
    }

    @Test
    public void getSecretMessageTest() {
        Mono<String> source = Mono.just("hello");

        StepVerifier
                .create(
                        ContextTestExample
                                .getSecretMessage(source)
                                .contextWrite(context ->
                                        context.put("secretMessage", "Hello, Reactor"))
                                .contextWrite(context -> context.put("secretKey", "aGVsbG8="))
                )
                .expectSubscription()
                .expectAccessibleContext()
                .hasKey("secretKey")
                .hasKey("secretMessage")
                .then()
                .expectNext("Hello, Reactor")
                .expectComplete()
                .verify();
    }

    @Test
    public void getCountryTest() {
        StepVerifier
                .create(RecordTestExample.getCapitalizedCountry(
                        Flux.just("korea", "england", "canada", "india")))
                .expectSubscription()
                .recordWith(ArrayList::new) // 파라미터로 전달한 java collection 에 emit 된 데이터를 추가
                .thenConsumeWhile(country -> !country.isEmpty()) // 파라미터로 전달한 Predicate 와 일치하는 데이터는 다음 단계에서 소비할 수 있도록
                .consumeRecordedWith(countries -> { // 컬렉션에 기록된 데이터를 소비
                    assertThat(
                            countries
                                    .stream()
                                    .allMatch(country ->
                                            Character.isUpperCase(country.charAt(0))),
                            is(true)
                    );
                })
                .expectComplete()
                .verify();
    }
}

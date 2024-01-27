package prac;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotSequence {
    public static void main(String[] args) throws InterruptedException {
        String[] singers = {"Adele", "Alicia Keys", "Aretha Franklin", "Whitney Houston", "Christina Aguilera", "Beyonce", "Lady Gaga", "Madonna", "Mariah Carey", "P!nk", "Rihanna", "Shakira"};

        Flux<String> concertFlux = Flux.fromArray(singers)
                .delayElements(Duration.ofSeconds(1))
                .share()
                .log();
                // cold sequence 를 hot sequence 로 변환, share 를 사용하면, 구독자가 생길 때마다 새로운 시퀀스를 생성하지 않고, 기존의 시퀀스를 공유한다.
                // => 여러 Subscriber 가 하나의 원본 Flux 를 공유한다

        concertFlux.subscribe(s -> System.out.println("Subscriber 1: " + s));

        Thread.sleep(2500);

        concertFlux.subscribe(singers2 -> System.out.println("Subscriber 2: " + singers2));

        Thread.sleep(2500);

        concertFlux.subscribe(singers3 -> System.out.println("Subscriber 3: " + singers3));

        Thread.sleep(10000);

    }
}

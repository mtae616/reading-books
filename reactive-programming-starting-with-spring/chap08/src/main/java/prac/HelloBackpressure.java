package prac;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class HelloBackpressure {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnRequest(data -> System.out.println(" # request: " + data))
                .log()
                .subscribe(new BaseSubscriber<Integer>() {

                    @Override
                    // onSubscribe 를 대신해 구독 시점에 request 메서드를 호출해서 최초 데이터 요청 개수를 제어
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    // onNext 를 대신해 Publisher 가 emit 한 데이터를 전달 받아 처리한 후에 Publisher 에게 또다시 데이터를 요청
                    protected void hookOnNext(Integer value) {
                        try {
                            Thread.sleep(2000L); // Publisher 의 데이터 속도보다 느리게 처리 가정
                            System.out.println("# hookOnNext: " + value);
                            request(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
    }
}

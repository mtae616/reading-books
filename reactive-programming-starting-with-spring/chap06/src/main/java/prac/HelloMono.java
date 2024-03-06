package prac;

import reactor.core.publisher.Mono;

public class HelloMono {
    public static void main(String[] args) {
        Mono.just("Hello Mono") // Hello Mono 라는 데이터를 emit
                .map(String::toUpperCase) // 데이터를 대문자로 변환
                .subscribe(System.out::println); // Hello Mono -> HELLO MONO

        Mono.empty() // empty 는 대체로 작업을 통해 데이터를 전달받을 필요는 없지만 작업이 끝났음을 알리고 이에 따른 후처리를 하고 싶을 때 사용한다.
                .subscribe(
                        none -> System.out.println("emitted onNext signal"), // onNext
                        error -> {},  // onError
                        () -> System.out.println("emitted onComplete signal") // onComplete
                        // data 를 emit 하지 않고 onComplete signal 만 emit 한다.
                );

    }
}

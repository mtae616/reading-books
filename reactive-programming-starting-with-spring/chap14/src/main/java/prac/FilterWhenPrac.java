package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FilterWhenPrac {

    private static final Map<String, Integer> map = new HashMap<>();

    static {
        map.put("a", 100);
        map.put("b", 200);
        map.put("c", 300);
    }

    public static void main(String[] args) throws InterruptedException {
        Flux.fromIterable(map.entrySet())
                // filterWhen Operator 는 내부에서 Inner Sequence 를 통해 조건에 맞는 데이터인지를 비동기적으로 테스트 한 후 테스트 결과가 true 라면 filterWhen 의 Upstream으로부터 전달 받은 데이터를 Downstream 으로 emit
                .filterWhen(entry -> Mono.just(entry.getValue() > 100)
                                        .publishOn(Schedulers.parallel())
                )
                .subscribe(data -> log.info("data: {}", data));

        Thread.sleep(1000);
    }

}

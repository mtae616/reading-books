package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Slf4j
public class SinkOnePrac {
    public static void main(String[] args) {
        Sinks.One<String> one = Sinks.one();
        // emit 한 데이터 구독하여 전달받기 위해서 asMono()) 메서드를 사용해서 변환
        Mono<String> mono = one.asMono();

        // FAIL_FAST : 에러가 발생했을 때 재시도를 하지 않고 즉시 실패를 처리한다. Thread safe, deadlock 방지
        one.emitValue("Hello", Sinks.EmitFailureHandler.FAIL_FAST);
        // Wrold sms drop 됨, 처음 emit 한 데이터는 정상적으로 emit 되지만 나머지 데이터들은 Drop 됨
        one.emitValue("World", Sinks.EmitFailureHandler.FAIL_FAST);

        mono.subscribe(data -> log.info(" # data1 : {}", data));
        mono.subscribe(data -> log.info(" # data2 : {}", data));

    }
}

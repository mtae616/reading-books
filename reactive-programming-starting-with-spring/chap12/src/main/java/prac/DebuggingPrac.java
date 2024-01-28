package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DebuggingPrac {

    public static final Map<String, String> fruits = new HashMap<>();

    static {
        fruits.put("apple", "사과");
        fruits.put("banana", "바나나");
        fruits.put("pear", "배");
        fruits.put("grape", "포도");
    }

    public static void main(String[] args) throws InterruptedException {
        // debug 모드 활성화
//        java.lang.NullPointerException: The mapper [prac.DebuggingPrac$$Lambda$32/0x000000080109a4e8] returned a null value.
//                at reactor.core.publisher.FluxMapFuseable$MapFuseableSubscriber.onNext(FluxMapFuseable.java:115)
//        Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException:
//        Assembly trace from producer [reactor.core.publisher.FluxMapFuseable] :
//        reactor.core.publisher.Flux.map(Flux.java:6517)
//        prac.DebuggingPrac.main(DebuggingPrac.java:31)
//        Error has been observed at the following site(s):
//	*__Flux.map ⇢ at prac.DebuggingPrac.main(DebuggingPrac.java:31)
//	|_ Flux.map ⇢ at prac.DebuggingPrac.main(DebuggingPrac.java:32)
        Hooks.onOperatorDebug();

        Flux.fromArray(new String[]{"APPLES", "BANANAS", "PEARS", "MELONS"})
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .map(String::toLowerCase)
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .map(fruits::get)
                .map(translate -> "맛있는 " + translate)
                .subscribe(log::info, error -> log.error(" # onError", error));

        Thread.sleep(100L);
    }
}

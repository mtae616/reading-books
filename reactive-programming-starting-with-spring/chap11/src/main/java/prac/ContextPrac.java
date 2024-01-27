package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ContextPrac {
    public static void main(String[] args) throws InterruptedException {
        Mono.deferContextual(ctx ->
                        Mono.just("Hello " + ctx.get("firstname"))
                                .doOnNext(data -> log.info(" # just doOnNext: {}", data))
                )
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .transformDeferredContextual(
                        (mono, ctx) -> mono.map(data -> data + " " + ctx.get("lastname"))
                )
                .contextWrite(context -> context.put("lastname", "Jobs"))
                .contextWrite(context -> context.put("firstname", "Steve"))
                .subscribe(data -> log.info(" # onNext: {}", data));

        Thread.sleep(100L);
    }
}

package prac;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

@Slf4j
public class ContextPrac2 {
    public static void main(String[] args) throws InterruptedException {
        final String key1 = "key1";
        final String key2 = "key2";
        final String key3 = "key3";

        Mono.deferContextual(ctx ->
                        Mono.just(ctx.getOrEmpty(key1) + ", " + ctx.get(key2) + " " + ctx.getOrDefault(key3, "no lastname"))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(
                        context -> (Context) context.putAll(Context.of(key2, "Steve", key3, "Jobs")).readOnly()
                )
//                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info(" # onNext: {}", data));

        Mono.deferContextual(ctx -> Mono.just(ctx.get(key1)))
                .contextWrite(context -> context.put(key1, "Apple"))
                .contextWrite(context -> context.put(key1, "Google"))
                .subscribe(data -> log.info(" # onNext: {}", data));

        Thread.sleep(100L);
    }
}

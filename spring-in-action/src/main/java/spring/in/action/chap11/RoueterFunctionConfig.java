package spring.in.action.chap11;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.just;

@Configuration
public class RoueterFunctionConfig {

    @Bean
    public RouterFunction<?> helloRouterFunction() {
        // <> bodyValue 는 non-reactive value 를 reactive value 로 바꾸어 준다.
        return route().GET("/hello", request -> ok().body(Mono.just("Hello World!"), String.class))
                .build();
    }

    @Bean
    public RouterFunction<?> helloRouterFunction2() {
        return route(GET("/hello2"), request -> ok().body(just("Hello World!"), String.class))
                .andRoute(GET("bye"), request -> ok().body(just("See ya!"), String.class));
    }

}

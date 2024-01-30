# Functional Endpoint

```java
 @FunctionalInterface
    public interface HandlerFunction<T extends ServerResponse> {
        Mono<T> handle(ServerRequest request);
    }
```
- `HandlerFunction`
  - Servlet 의 `service(ServletRequest req, ServletResponse res)` 와 유사
    - Servlet 은 req, res 를 parameter 로 받는 데에 반해 parameter 로 request 만 받고 response 나감
    - `ServerRequest`
      - HandlerFunction 에 의해 처리되는 HTTP Request
      - HTTP header, method, URI, query parameter 등을 포함
      - body 에 접근하기 위해 `body()`, `bodyToMono()`, `bodyToFlux()` 등의 메소드 제공
    - `ServerResponse`
      - HandlerFunction 또는 HandlerFilterFunction 에 의해 생성되는 HTTP Response
      - HTTP header, status code, body 등을 포함

<br/>

```java
@FunctionalInterface
public interface RouterFunction<T extends ServerResponse> {
    Mono<HandlerFunction<T>> route(ServerRequest request);
    
    // ...
}
```

- `RouterFunction`
  - `route()` 메서드에서 파라미터로 전달받은 request 에 매치되는 HandlerFunction 을 리턴
  - v1 참고

```java
@Configuration("bookRouterV1")
public class BookRouter {

    @Bean
    public RouterFunction<?> routeBookV1(BookHandler handler) {
      // RouterFunctionBuilder 객체를 리턴, RouterFunctionBuilder 객체로 각각의 HTTP Method 에 대한 request 를 처리하기 위한 route 추가
        return route() 
                // 아래 각 메서드에서 DI 받은 handler 를 통해 request 를 처리
                .POST("/v1/books", handler::createBook)
                .PATCH("/v1/books/{book-id}", handler::updateBook)
                .GET("/v1/books", handler::getBooks)
                .GET("/v1/books/{book-id}", handler::getBook)
                .build();

//        return route(POST("/v1/books"), handler::createBook)
//                .andRoute(GET("/v1/books/{book-id}"), handler::getBook)
//                .andRoute(PATCH("/v1/books/{book-id}"), handler::patchBook)
//                .andRoute(GET("/v1/books"), handler::getBooks);
    }
}
```

- 함수형 엔드포인트에서의 request body 유효성 검증
  - `v2/BookHandler, BookValidator` 참고
  - `v4/BookValidator, BookHandler` 참고
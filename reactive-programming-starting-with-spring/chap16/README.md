# Annotated Controller

```java
// before
@PatchMapping("/{book-id}")
public Mono patchBook(@PathVariable("book-id") long bookId,
                      @RequestBody BookDto.Patch requestBody) {
  requestBody.setBookId(bookId);
  Mono<Book> book =
          bookService.updateBook(mapper.bookPatchToBook(requestBody));

  return mapper.bookToBookResponse(book);
}

// after
@PatchMapping("/{book-id}")
public Mono patchBook(@PathVariable("book-id") long bookId,
                      @RequestBody Mono<BookDto.Patch> requestBody) {
  Mono<Book> result = bookService.updateBook(bookId, requestBody);
  return result.flatMap(book -> Mono.just(mapper.bookToResponse(book)));
}
```

- 위의 코드처럼 body 를 mono 로 받을 수 있음
  - body 를 parsing 할 때 I/O 가 발생하기 때문에 동기적인 동작이 발생할 수 있음
  - 따라서 Mono 로 비동기적 동작 유도
  - 참고
    - 각주 : pathvariable / query parameter 는 io 작업이 발생하지 않으니까 따로 mono 로 받을 필요 없음

<br />

- 참고 
  - 각주 : `DispatcherHandler` 가 어떻게 Controller 를 구독할 수 있는지

```java
// target
@GetMapping("/{book-id}")
public Mono getBook(@PathVariable("book-id") long bookId) {
  return bookService.findBook(bookId)
          .flatMap(book -> Mono.just(mapper.bookToResponse(book))).log();
}

public class WebFluxSimulator {
    public static void main(String[] args) {
        // 핸들러 메서드를 래핑하는 HandlerFunction
        HandlerFunction<ServerResponse> handlerFunction = request -> {
            // 핸들러 메서드 호출
            Mono<ServerResponse> responseMono = getBook(request);
            return responseMono;
        };
      
        // 서버 요청을 처리하는 부분 (DispatcherHandler 역할)
        ServerRequest request = ServerRequest.create(null, null);
        handlerFunction.handle(request)
                .subscribe(response -> {
                    // 여기서 구독이 발생하고, 실제로 데이터를 전송하는 로직이 실행됨
                });
    }
}

```
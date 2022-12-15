# 예외 처리 Exception handling

1. 컴파일 에러 : 컴파일 시에 발생하는 에러
2. 런타임 에러 : 실행 시에 발생하는 에러
3. 논리적 에러 : 실행은 되지만 의도와 다르게 동작하는 것

<br />

1. 에러 error : 프로그램 코드에 의해서 수습될 수 없는 심각한 오류
2. 예외 exception : 프로그램 코드에 의해서 수습될 수 있는 다소 미약한 오류
   - Exception클래스들 : 사용자으 ㅣ실수와 같은 외적인 요인에 의해 발생하는 예외
   - RuntimeException클래스들 : 프로그래머의 실수로 발생하는 예외
     - printStackTrace() : 예외발생 당시의 호출스택에 있었던 메서드의 정보와 예외 메세지를 화면에 출력
     - getMessage() : 발생한 예외클래스의 인스턴스에 저장된 메세지를 얻을 수 있다.

<br />

- 예외는 try-catch로 잡거나 throws로 넘긴다.
- <a href="https://liltdevs.tistory.com/138">관련 posting</a>
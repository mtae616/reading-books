# 자바 Java Programming Language

- 운영체제 독립적
  - JVM
    - Java app -> jvm
  - oop
    - object-oriented programming language
  - 쉽게 배울 수 있다.
  - gc (Garbage collection)
  - 네트워크와 분산 처리
  - 멀티쓰레드
    - java interperter -> scheduling

## JVM Java Virtual Machine

- 자바를 실행하기 위한 가상 머신
- virtual computer == logical computer
- 자바 파일 컴파일 시 하드웨어에 맞게 완전히 컴파일 된 상태가 아니고 실행 시에 해석 된다
- JIT : 바이트코드(컴파일된 자바코드) -> 기계어

## 자바로 프로그램 작성하기
1. Hello.java 작성 
2. 자바컴파일러(javac.exe)로 컴파일 
3. 클래스파일(Hello.class) == 바이트코드 
4. 자바 인터프리터(java.exe) 로 실행

위와 같이 컴파일 되면 다시 다음과 같은 실행 과정을 거친다.

1. 프로그램의 실행에 필요한 클래스(*.class파일)을 로드한다.
2. 클래스 파일을 검사한다. (파일형식, 악석코드 체크)
3. 지정된 클래스 (Hello)에서 main(String[] agrs)를 호출한다.
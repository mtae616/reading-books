# 메모리 때문에 발생할 수 있는 문제들
- Java 의 메모리 구조
  - PC 레지스터 : 스레드별 보유
    - 각 스레드의 JVM 인스트럭션의 주소가 저장되어 있다.
  - JVM 스택 : 스레드별 보유
    - 스레드가 생성되면서 동시에 생성
    - 지역 변수와 부분 결과를 저장, 메서드 호출 및 리턴과 관련된 정보가 보관된다.
  - 힙 : 대부분의 데이터가 저장되는 일반적인 저장소
    - 모든 클래스의 인스턴스와 배열
    - jvm 이 시작될 때 생성되며 가비지 컬렉터에 의해서 관리된다.
  - 메서드 영역
    - 모든 jvm 의 스레드에 공유하며 각 클래스의 구조 정보를 저장하는 영역
    - 런타임 상수 풀, 필드, 메서드 데이터, 메서드와 생성자의 코드, 클래스와 인터페이스 인스턴스 초기화를 위한 특수 메서드 들에 대한 정보
  - 런타임 상수 풀 : 메서드 영역에 할당되어 있음
    - 각각의 클래스 및 인터페이스에 대한 constant_pool 테이블을 실행 시 참고하기 위한 저장소
    - 여러 종류의 상수들을 저장한다.
  - 네이티브 메서드 스택
    - 자바 언어 이외의 네이티브 언어를 호출할 경우 타 언어의 스택 정보를 여기에 저장

## OutOfMemoryError 는 언제 발생할까?
- exception < error 
- java.lang.VirtualMachineError.OutOfMemoryError
  - VirtualMachineError 클래스는 Absctract 로 정보를 볼 수 없음
  - JVM 에 문제가 생겼거나 (broken)
  - 작업을 계속해서 진행할 리소스가 부족할 때 발생한다
- 가비지 컬레겉가 새로운 객체를 생성할 공간을 더 이상 만들어주지 못하고 더 이상 힙 영역의 메모리가 증가될 수 없을 때
- 네이티브 라이브러리 코드에서 swap 영역이 부족하여 더 이상 네이티브 할당을 할 수 없을 때


## OutOfMemoryError 메시지의 의미
- `Exception in thread "main": java.lang.OutOfMemoryError: Java heap space`
  - 힙 영역에 더 이상 객체를 생성하기 어려울 때
  - 메모리 크기를 너무 적게 잡아 놓처가 아예 메모리 크기를 지정하지 않은 경우
    - Xms, Xmx
  - 오래된 객체들이 계속 참조되고 있어서 GC가 되지 앟은 경우
    - static 을 잘못 사용하는 경우나, 객체를 지속해서 참조할 경
  - finalize 메서드를 개발자가 개발한 클래스에 구현해 놓은 경우
    - GC 가 발생하기 전에 GC 가 되어야 하는 객체들은 큐에 쌓이도록 되어 있다
    - GC 를 처리하기 위한 데몬 스레드가 있고, 이 스레드가 청소할 객체들을 처리하기 전에 finalize 큐에 너무 많은 객체가 보관되어 있고
    - 처리도 불가능할 경우 문제가 발생할 수 있다.
    - 이런 문제는 발생 빈도가 낮지만 개발자가 해당 메서드를 구현하여 별도의 작업을 할 때 발생하기도 한다.
  - 스레드의 우선순위를 너무 높일 경우
    - 개발된 프로그램의 스레드 우선순위를 너무 높게 지정해 놓으면 GC 를 처리하는 속도보다 우선순위가 높은 스레드를 메모리에 생성하는 속도가 더 빨라 문제가 발생할 수 있다.
  - 큰 덩어리의 객체가 여러개 있을 경우
    - 하나에 100MB 인데 힙이 250 인 경우
- `Exception in thread "main": java.lang.OutOfMemoryError: Metaspace`
  - 너무 많은 클래스가 로딩된 경우
  - `XX:MaxMetaspaceSize=128m` 으로 셋팅 가능
- `Exception in thread "main": java.lang.OutOfMemoryError: Requested array size exceeds VM limit`
  - 배열의 크기가 힙 영역의 크기보다 더 크게 지정되었을 때
- `Exception in thread "main": java.lang.OutOfMemoryError: request <size> bytes for <reason>. Out of swap space?`
  - native heap 이 부족한 상황에 swap space 까지 부족한 상황...
  - 개발된 자바 애플리케이션에서 호출하는 네이티브 메서드에서 메모리를 반환하지 않은 경우
  - 다른 애플리케이션에서 메모리를 반환하지 않은 경우
- `Exception in thread "main": java.lang.OutOfMemoryError: <reason> <stacktrace> (Native method)`
  - 위 처럼 네이티브 힙 영역 관련

## 메모리 릭의 세 종류
- 수평적 메모리 릭 (horizontal memory leak)
  - 하나의 객체에서 매우 많은 객체를 참조하는 경우
  - ArrayList
- 수직적 메모리 릭 (vertical memory leak)
  - 각 객체들이 링킹되어 있을 때
  - LinekdList 
- 대각선 메모리 릭 (diagonal memory leak)
  - 복합적으로 메모리를 점유하고 있을 때


## GC 를 발생시키지 않으려면...
- 임시 메모리의 사용 최소화
- 객체의 재사용
- XML 처리 시 메모리를 많이 점유하는 DOM 보다 SAX 를 사용
- 너무 많은 데이터를 한 번에 보여주는 비즈니스 로직 제거
- 기타 프로파일링을 통하여 임시 메모리를 많이 생성하는 부분 제거
# JAVA.lang패키지와 유용한 클래스

- equals
  - 참조변수의 값을 주소값 대신 커스텀한 값으로 비교하기 위해 오버라이딩 하여 사용
- hashCode
  - Equals를 통해 비교할 때 같은 해시값을 갖게 하기 위해 오버라이딩
- clone
  - shallow copy
- getClass
  - 바이트코드로 저장되어 있는 클래스를 읽어 Class클래스에 정의된 형식으로 변환

```java
String a = "AAA";
String b = "AAA";
String c = "AAA";
```
- 위와 같은 상황에서 String 리터럴들은 컴파일 시에 클래스 파일에 저장됨
- AAA를 담고있는 String 인스턴스 하나가 생성된 후 a,b,c는 같은 인스턴스를 참조함
- 클래스파일이 로딩될 때 리터럴들은 JVM내 contant pool에 저장되어 있음

<br />

- StringBuffer는 thread safe하도록 동기화 된다.
- 반면 StringBuilder는 동기화만 뺀 동일한 동작을 한다.
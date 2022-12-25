# Generics, Enumeration, Annotation

## Generics

- 다양한 타입의 객체즐을 다루는 메서드나 컬렉션 클래스에 컴파일 시의 타입 체크를 해주는 기능
  - 타입 안정성을 높인다.
  - 타입체크와 형변환을 생략할 수 있으므로 코드가 간결해진다.
  - Box<T> : 제네릭 클래스 T의 Box 또는 T Box 라고 읽는다.
    - T : 타입 변수 또는 타입 매개변수
    - Box : 원시 타입
  - Generic 클래스 내부에서 T[] 은 new로 생성할 수 없다
    - 컴파일 시점에 T가 어떤 타입이 될지 모르기 때문에
    - instanceof 연산자도 마찬가지이다.
    - 따라서 newInstance() 나 Object 배열을 생성해서 복사한 다음 T[]로 형변환 하는 방법을 사용해야 한다.
  - Generic wildcard
    - &lt;? extends T> : 와일드 카드의 상한 제한 T와 그 자손들만 가능
    - &lt;? super T> : 와일드 카드의 하한 제한 T와 그 조상들만 가능
    - &lt;?> : 제한 없음 모든 타입 가능 <T extends Object> 와 동일

## Enums

- 열거형은 서로 관련된 상수를 편리하게 선언하기 위한 것
- 열거형 상수간의 비교는 == 를 사용 -> 빠른 성능을 제공
  - 비교연산자는 사용할 수 없고
  - compareTo는 사용가능
- 멤버를 사용할 때에는 생성자로 추가해줘야 한다.
```java
class Direction {
    EAST(1, ">"), SOUTH(2, "V"),;
    private final int value;
    private final String symbol;
    Direction(int value, String symbol) {
      this.value = value;
      this.symbol = symbol;
    }
    public int getValue() { return this.value; }
    public String getSymbol() { return this.symbol;} 
}
```

```java
    enum Transportation {
        BUS(100) { // implement abstract function
            int fare(int distance) { return distance * BUS.BASIC_FARE; }
        },
        TRAIN(150) { // implement abstract function
            int fare(int distance) { return distance * TRAIN.BASIC_FARE; }
        },
        SHIP(100) { // implement abstract function
            int fare(int distance) { return distance * SHIP.BASIC_FARE; }
        },
        AIRPLANE(300) { // implement abstract function
            int fare(int distance) { return distance * AIRPLANE.BASIC_FARE; }
        };

        private final int BASIC_FARE;

        Transportation(int BASIC_FARE) {
            this.BASIC_FARE = BASIC_FARE;
        }

        abstract int fare(int distance); // abstract function

        public int getBASIC_FARE() {
            return BASIC_FARE;
        }
    }
```

## Annotation

- 소스코드에 주석을 적어두고 javadoc이 정보를 읽어서 사용
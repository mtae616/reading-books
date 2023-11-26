# 상속과 코드 재사용
- 상속 / 합성

## 상속과 중복 코드
- DRY 원칙
  - Don't Repeat Yourself
  - 중복 여부 판단하는 기준은 변경이다
  - 요구사항이 변경됐을 때 두 코드를 함께 수정해야 한다면 코드는 중복이다
  - == Once and Only Once, Single-Point Control
- 중복과 변경
  - 타입 (enum) 을 사용하여 분기시키는 방식은 낮은 응집도와 높은 결합도라는 문제에 시달리게 된다.
  ```java
      public Money calculateFee() {
        Money result = Money.ZERO;

        for(Call call : calls) {
            if (type == PhoneType.REGULAR) { // 낮은 응집, 타입에 따른 분기, 시간에 따른 분기
                result = result.plus(amount.times(call.getDuration().getSeconds() / seconds.getSeconds()));
            } else {
                if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) { // 높은 결합, 디미터 위반, tell don't ask 위반 
                    result = result.plus(nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds()));
                } else {
                    result = result.plus(regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds()));
                }
            }
        }

        return result;
    }
  ```
  - 상속을 위한 경고1
    - 자식 클래스의 메서드 안에 super 참조를 이용해 부모 클래스의 메서드를 직접 호출할 경우 두 클래스는 강하게 결합된다.
    - super 호출을 제거할 수 있는 방법을 찾아 결합도를 제거하라

## 취약한 기반 클래스 문제
- 상속은 자식 클래스와 부모 클래스의 결합도를 높인다.
- 취약한 기반 클래스 문제 (Fragile Base Class Problem, Brittle Base Class Problem)
  - 상속이라는 context 에서 결합도가 높아져 초래하는 문제점 
  - 자식/부모 간 불필요한 세부사항에 엮이게 된다.
    - 부모 클래스의 작은 변경에도 자식 클래스는 compile error 생길 수 있음
  - 상속을 사용한다면 피할 수 없는 객체지향 프로그래밍의 근본적인 취약성
  - 부모클래스에 의존하게 만드므로 캡슐화를 약화시키고 결합도를 높인다
- 불필요한 인터페이스 상속 문제
  - Vector - Stack
    - Stack 은 push, pop 만 해야 되는데 Vector 를 상속하면서 index 로도 가능
  - 상속을 위한 경고2
    - 상속받은 부모 클래스의 메서드가 자식 클래스의 내부 구조에 대한 규칙을 깨트릴 수 있다.
- 메서드 오버라이딩 오작용 문제
  - 상속을 위한 경고 3
    - 자식 클래스가 부모 클래스의 메서드를 오버라이딩할 경우 부모 클래스가 자신의 메서드를 사용하는 방법에 자식 클래스가 결합될 수 있다.
- 부모 클래스와 자식 클래스의 동시 수정 문제
  - 코드 재사용을 위한 상속은 부모 클래스와 자식 클래스를 강하게 결합시키기 때문에 함께 수정해야 하는 상황 역시 빈번하다
  - 상속을 위한 경고 4
    - 클래스를 상속하면 결합도로 인해 자식 클래스와 부모 클래스의 구현을 영원히 변경하지 않거나, 자식 클래스와 부모 클래스를 동시에 변경하거나 둘 중 하나를 선택할 수밖에 없다.

## Phone 다시 살펴보기
- 추상화에 의존하자
  - 상속이 아닌 추상화에 의존하게 하자
  - 코드 중복을 제거하기 위해 상속을 도입할 때 따르는 두 가지 원칙
    - 두 메서드가 유사하게 보인다면 차이점을 메서드로 추출하라, 추출을 통해 두 메서드를 동일한 형태로 보이도록 만들 수 있다.
    - 부모 클래스의 코드를 하위로 내리지 말고 자식 클래스의 코드를 상위로 올려라, 부모 클래스의 구체적인 메서드를 자식 클래스로 내리는 것보다 자식 클래스의 추상적인 멧더르르 부모 클래스로 올리는 것이 재사용성과 응집도 측면에서 더 뛰어난 결과를 얻을 수 있다.
- 차이를 메서드로 추출하라
  - 중복 코드 안에서 차이점을 별도의 메서드로 추출
  - 변하는 것으로부터 변하지 않는 것을 분리하라
  - 변하는 부분을 찾고 이를 캡슐화하라
- 중복 코드를 부모 클래스로 올려라
  - 공통 코드를 부모 클래스로 올린다.
- 추상화가 핵심이다
- 의도를 드러내는 이름 선택하기

## 차이에 의한 프로그래밍
- 기존 코드와 다른 부분만을 추가함으로써 애플리케이션의 기능을 확장하는 방법

# 의존성 관리하기
- 좋은 app, 작고 응집도 높은 객체로 구성
- 협력을 위해 필요한 의존성은 유지, 변경에 방해하는 의존성은 제거

## 의존성 이해하기
- 변경과 의존성
  - 의존성
    - 실행 시점 : 의존하는 객체가 정상적으로 동작하기 위해서는 실행 시 의존 대상 객체가 반드시 존재해야 한다
    - 구현 시점 : 의존 대상 객체가 변경될 경우 의존하는 객체도 함께 변경된다.
    ```java
    public class PeriodCondition implements DiscountCondition {
        private DayOfWeek dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;
    
        // screening 이 있어야 실행 가능
        // 의존성은 항상 단방향
        // 이 경우 screening 이 변경될 때 periodcondition 이 영향을 받게 되지만 그 역은 성립하지 않는다
        public boolean isSatisfiedBy(Screening screening) {
            return screening.getStartTime().getDayOfWeek().equals(dayOfWeek) &&
                    startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0&&
                    endTime.compareTo(screening.getStartTime().toLocalTime()) >= 0;
        }
    }
    ```
    - 의존성은 의존되는 요소가 변경될 때 의존하는 요소도 함껩 ㅕㄴ경될 수 있다는 것을 의미한다.
- 의존성 전이
  - 의존성은 전이될 수 있다
  - PeriodCondition-Screening-movie
    - 직접 의존성
      - PeriodCondition-Screening
    - 간점 의존성
      - PeriodCondition-movie
- 런타임 의존성과 컴파일타임 의존성
  - 런타임 의존성
    - 애플리케이션 실행되는 시점
    - 객체
    - movie 는 amountdiscountpolicy 나 percentdiscountpolicy 에 의존한다
  - 컴파일타임 의존성
    - 작성된 코드를 컴파일 하는 시점 / 코드 그 자체
    - 클래스
    - movie 는 amountdiscountpolicy / percentdiscountpolicy 에 의존하지 않는다
      - dicountpolicy 에만 의존한다
      - movie 가 두 클래스 모두를 알지 못하고 discountpolicy 라는 추상 클래스에만 의존하게하고 컴파일타임 의존성을 실행 시에 런타임 의존성으로 대체하여 두 인스턴스와 함꼐 협력할 수 있다.
- 컨텍스트 독립성
  - 클래스는 자신과 협력할 객체의 구체적인 클래스에 대해 알아서는 안된다.
  - 구체적인 클래스를 알면 알수록 그 클래스가 사용되는 특정한 문맥에 강하게 결합되기 때문에
  - 클래스가 특정한 문맥에 강하게 결합될 수록 재사용 성은 떨어진다.
  - 클래스가 사용될 특정한 문맥에 대해 최소한의 가정만으로 이뤄져 있다면 다른 문맥에서 재사용하기 수월하고 이를 컨텍스트 독립성이라고 한다
- 의존성 해결
  - 컴파일타임 의존성을 실행 컨텍스트에 맞는 적절한 런타임 의존성으로 교체하는 것
    - 생성자
    - setter
    - method parameter

## 유연한 설계
- 의존성과 결합도
  - 바람직한 의존성은 재사용성과 관련이 있다
  - 컨텍스트 독립성 의존성은 바람직한 의존성
  - 느슨한 결합
- 지식이 결합을 낳는다
  - 서로에 대해 알고 있는 지식의 양이 결합도를 결정한다
- 추상화에 의존하라
  - DIP
  - 명시적 의존성 (discountpolicy)
  - 숨겨진 의존성 (amountdiscountpolicy / percentdiscountpolicy)
- new 는 해롭다
  - 구체 클래스의 이름을 직접 기술해야 한다, 따라서 new 를 사용하는 클라이언트는 추상화가 아닌 구체 클래스에 의존할 수 밖에 없다
  - new 연산자는 생성하려는 구체 클래스뿐만 아니라 어떤 인자를 이용해 클래스의 생성자를 호출해야 하는지도 알아야 한다. 따라서 new 를 사용하면 클라이언트가 알아야 하는 지식의 양이 늘어나기 대문에 결합도가 높아진다.
  - 가끔은 괜챃음...
- 표준 클래스에 대한 의존은 해롭지 않다
  - jdk 에 포함된 클래스
- 컨텍스트 확장하기
- 조합 가능한 행동
  - 유연하고 재사용 가능한 설계는 작은 객체들의 행동을 조합함으로써 새로운 행동을 이끌어내는 설계

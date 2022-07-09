package InformationHidingAndEncapsulation;

public class AccessLevelModifiers {
    // 클래스 정의 대상 : public, default
    // 인스턴스 변수와 메소드 대상 : public, protected, private, default

    // 클래스
    // public : 어디서든 객체 생성 가능
    // default : 동일 패키지로 묶인 클래스 내에서만 객체 생성 허용, 아무것도 선언되지 않은 것이랑 동일

    // 인스턴스, 메소드
    //             | 클래스 내부 | 동일 패키지 | 상속 받은 클래스 | 이외의 영역
    // public           o           o           o           o
    // protected        o           o           o           x
    // default          o           o           x           x
    // private          o           x           x           x

    // final : 상수




}

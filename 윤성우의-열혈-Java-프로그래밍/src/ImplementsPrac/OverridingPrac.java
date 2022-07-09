package ImplementsPrac;

class MobilePhone {
    protected String number; // 전화번호

    public MobilePhone(String number) {
        this.number = number;
    }
    public void answer() {
        System.out.println("Hi~ from " + number);
    }
}

class SmartPhone extends MobilePhone {
    private String androidVer; // 안드로이드 운영체제 네임

    public SmartPhone(String number, String androidVer) {
        super(number);
        this.androidVer = androidVer;
    }
    public void playApp() {
        System.out.println("App is running in " + this.androidVer);
    }
}

public class OverridingPrac {
    public static void main(String[] args) {
        // 하위 클래스는 상위 클래스의 모든 특성을 갖고, 자신만의 추가적인 특성을 더한다.
        // SmartPhone Extends MobilePhone
        SmartPhone phone = new SmartPhone("010-1234-1234", "Nougat");
        phone.answer();
        phone.playApp();

        // MobilePhone형 참조변수가 SmartPhone 인스턴스를 참조
        // MobilePhone을 상속하는 SamrtPhone 인스턴스는 MobilePhone 인스턴스이기도 하다
        // 따라서 MobilePhone형 참조변수는 SmartPhone 인스턴스를 참조할 수 있다.
        // 참조변수의 형에 상관없이 참조하는 인스턴스에 따라 접근가능한 멤버가 결정되지 않는 이유는
        // 컴파일 단계에서 쉽게 판단할 수 있다. == 실행시간이 단축된다.
        // 자바는 메소드 호출 시 참조변수의 타입을 참조하여 그 메소드 호출이 옳은 것인지 판단
        // 참조변수의 형을 기준으로 접근 가능한 멤버를 제한하는 것은 코드를 단순하게 한다.
        MobilePhone phone2 = new SmartPhone("010-4567-4567", "Nopugat");
        phone2.answer();
//        phone2.PlayApp(); 이거 안됨...
        // 참조는 SmartPhone 으로 되지만 타입이 MobilePhone이라서...
//        SmartPhone phone3 = (SmartPhone) new MobilePhone("asd");
//        phone3.playApp(); 이거는 되긴 되는데 컴파일 오류남... 당연하지 ㅂㅅ아
    }
}

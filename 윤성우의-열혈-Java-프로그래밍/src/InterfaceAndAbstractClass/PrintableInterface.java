package InterfaceAndAbstractClass;

interface Printable {
    void print(String doc);
    default void printCMYK(String doc) {
        System.out.println(doc);
    }
    // 인터페이스의 디폴트 메소드
    // 자체로 완전한 메소드이다.
    // 오버라이딩 하지 않아도 된다.
    // 처음 인터페이스를 설계하면서는 디폴트 메소드를 사용하지 않는다.

    static void print2(String str) {
        System.out.println(str);
    }
    // class 의 static 과 동일하다.

}

class Printer implements Printable {
    @Override
    public void print(String doc) {
        System.out.println(doc);
    }

    @Override
    public void printCMYK(String doc) {
        Printable.super.printCMYK(doc);
    }
}

public class PrintableInterface {
    public static void main(String[] args) {
        // 한 클래스는 둘 이상의 인터페이스를 동시에 구현할 수 있다.
        // 상속과 구현은 동시에 가능하다.
        // interface의 모든 메소드는 public이 선언된 것으로 간주합니다.
        //interface의 모든 변수는 선언과 동시에 초기화 해야 하고, public, static, final이 선언된 것으로 간주한다.
        // interface는 다른 interface를 extends 키워드를 통해 상속받을 수 있다.
        Printable prn = new Printer(); // Printable형 참조변수 선언 가능
        prn.printCMYK("hello Java");
    }
}

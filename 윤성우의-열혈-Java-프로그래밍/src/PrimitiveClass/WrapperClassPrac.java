package PrimitiveClass;

public class WrapperClassPrac {
    public static void showData(Object obj) {
        System.out.println(obj);
    }
    public static void main(String[] args) {
        // 래퍼 클래스는 기본 자료형의 값을 감싸는 클래스이다.
        Integer iInst = new Integer(3); // 정수 3을 감싸는 래퍼 인스턴스 생성
        showData(iInst);
        showData(new Double(7.15)); // 7.15를 감싸는 래퍼 인스턴스 생성 및 전달
        // 래퍼 클래스도 toString 메소드를 오버라디이 하고 있다.
        // 따라서 인자가 sout에 인스턴스가 전달되면 값이 출력된다.
    }
}

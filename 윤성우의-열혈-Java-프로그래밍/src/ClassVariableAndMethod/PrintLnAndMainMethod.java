package ClassVariableAndMethod;

public class PrintLnAndMainMethod {
    public static void main(String[] args) {
        System.out.println(); // 은
        // out은 System.out 으로 접근하니 static으로 선언된 클래스 변수
        // public static final PrintStream out; // 참조변수 out
        // println 은 PrintStream 클래스의 인스턴스 메소드이다.
        // System에 위치한 클래스 변수(Printstram객체) out이 참조하는 인스턴스의 println 메소드를 호출하는 문장

        // public static void main
        // 클래스 내부에 있어도 main메소드는 사실상 별개이다
        // 클래스가 main 메소드에게 공간을 제공했을 뿐이다.
    }
}

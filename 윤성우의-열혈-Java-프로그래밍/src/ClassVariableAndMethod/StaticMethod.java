package ClassVariableAndMethod;

// 인스턴스 생성 이전부터 접근이 가능하다.
// 어느 인스턴스에도 속하지 않는다.
public class StaticMethod {
    public static void main(String[] args) {
        NumberPrinter.showInt(20); // 클래스 이름을 통한 클래스 메소드 호출

        NumberPrinter np = new NumberPrinter();
        np.showDouble(3.14);
        np.setMyNumber(75);
        np.showMyNumber();

        Test.addNum(3);
        Test.addNum(4);
        Test.showNum();
    }
}

class Test {
    public static int num = 0;
    // static이 아니면 아래 함수에서 참조 불가
    // 아마 static 함수라 외부에서 참조 못하게 막아둔 듯
    // 클래스 메소드(static method)는 인스턴스에 속하지 않으므로 인스턴스 변수에 접근이 불가능하다.
    // 같은 이유로 클래스 메소드(static method)는 인스턴스 메소드의 호출도 불가능하다.
    static void addNum(int n) {
        num += n;
    }
    public static void showNum() {
        System.out.println(num);
    }
}

class NumberPrinter {
    private int myNum;
    static void showInt(int n) {
        System.out.println(n);
    }
    static void showDouble(double n) {
        System.out.println(n);
    }
    void setMyNumber(int n) {
        myNum = n;
    }
    void showMyNumber() {
        showInt(myNum);
    }
}
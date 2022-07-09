package ImplementsPrac;

class SuperCLS2 {
    protected static int count = 0; // protected는 하위 클래스에서 접근 간으

    public SuperCLS2() {
        count++;
    }
}

class SubCLS2 extends SuperCLS2 {
    public void showCount() {
        System.out.println(count);
    }
}

public class ImplementsOfVariableAndMethods {
    public static void main(String[] args) {
        // 클래스 변수, 클래스 메소드(static variable, methods)
        // 인스턴스 생성과 상관 없이 접근 가능
        // 클래스 내부와 외부에서 접근 가능
        // 클래스 내에서 직접 접근 가능
        SuperCLS2 obj1 = new SuperCLS2(); // count ++
        SuperCLS2 obj2 = new SuperCLS2(); // count ++
        SubCLS2 obj3 = new SubCLS2(); // count ++
        obj3.showCount();
    }
}

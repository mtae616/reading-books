package GoalOfImplements;

class Cake {
    final int a = 0; // 상수 취금

    // Object 클래스의 toString 메소드를 오버라이딩
    public String toString() {
        System.out.println(super.toString());
        return "My birthday cake";
    }
}

class CheeseCake extends Cake {
    // Cake 클래스의 toString 메소드를 오버라이딩
    public String toString() {
        return "My birthday cheese cake";
    }
}

public class ObjectClassAndFinalAndOverride {
    public static void main(String[] args) {
        Cake c1 = new Cake();
        Cake c2 = new CheeseCake();

        // c1이 참조하는 인스턴스의 toString 메소드 호출로 이어짐
        System.out.println(c1);
        System.out.println();

        // c2가 참조하는 인스턴스의 toString 메소드 호출로 이어짐
        System.out.println(c2);
    }
}

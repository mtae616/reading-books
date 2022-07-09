package NestedClassAndLambda;

class Outer2 {
    private static int num = 0;
    // static 네스티드 클래스 내에서 외부 클래스의 인스턴스 변수와 메소드에 접근 불가능하다.
    static class Nested1 { // Static 네스티드 클래스
        void add(int n) { num += n; }
    }
    static class Nested2 { // static 네스티드 클래스
        int get() { return num; }
    }
}

public class StaticNestedClass {
    public static void main(String[] args) {
        Outer2.Nested1 nst1 = new Outer2.Nested1();
        nst1.add(5);
        Outer2.Nested2 nst2 = new Outer2.Nested2();
        System.out.println(nst2.get());
    }
}

package NestedClassAndLambda;

class Outer {
    private int num = 0;

    class Member {
        void add(int n) { num += n; }
        int get() { return num; }
    }
}

public class MemberInner {
    public static void main(String[] args) {
        // 이너 클래스는
        // 멤버 클래스 : 인스턴스 변수, 인스턴스 메소드와 동일한 위치에 정의
        // 로컬 클래스 : 중괄호 내에, 특히 메소드 내에 정의
        // 익명 클래스 : 이름이 존재하지 않는, 위의 두 클래스와 혼동할 일이 없는 클래스
        Outer o1 = new Outer();
        Outer o2 = new Outer();

        Outer.Member o1m1 = o1.new Member();
        Outer.Member o1m2 = o1.new Member();

        Outer.Member o2m1 = o2.new Member();
        Outer.Member o2m2 = o2.new Member();

        // 멤버 클래스의 인스턴스는 외부 클래스의 인스턴스에 종속적이다.
        // 클래스의 정의를 감추어야 할 때 유용하게 사용이 된다.
        o1m1.add(5);
        System.out.println(o1m2.get());

        o2m1.add(7);
        System.out.println(o2m2.get());
    }
}

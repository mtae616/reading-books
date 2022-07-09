package ImplementsPrac;

class Cake {
    public int size;

    public Cake(int size) {
        this.size = size;
    }

    public void yummy() {
        System.out.println("Yummy Cake");
    }

    public void showCakeSize() {
        System.out.println(size);
    }
}

class CheeseCake extends Cake {
    public int size;

    public CheeseCake(int size, int size1) {
        super(size);
        this.size = size1;
    }

    public void yummy() {
        super.yummy();
        System.out.println("Yummy Cheese Cake");
    }
    public void tasty() {
        super.yummy(); // cake 의 yummy 호출
        System.out.println("Yummy Tasty Cake");
    }

    public void showCakeSize() {
        System.out.println(super.size);
        System.out.println(this.size);
    }

}

class StrawberryCheeseCake extends CheeseCake {
    public StrawberryCheeseCake(int size, int size1) {
        super(size, size1);
    }
}

public class MethodOverriding {
    public static void main(String[] args) {
//        Cake c1 = new CheeseCake();
//        CheeseCake c2 = new CheeseCake(5, 7);
//        c1.yummy(); // overriding 한 yummy cheese cake 호출
//        c2.yummy();
        // 메소드 이름, 반환형, 매개변수가 같아야 오버라이딩이 성립한다.
//        c2.yummy();
//        c2.tasty();
        CheeseCake ca1 = new CheeseCake(5, 7);
        Cake ca2 = ca1;

        // ca2는 Cake형이므로 ca2.size는 Cake의 멤버 size를 의미함
        System.out.println(ca2.size); // 5

        // ca1은 cheesecake형이므로 ca1.size는 cheesecake의 멤버 size를 의미함
        System.out.println(ca1.size); // 7

        // 따라서 참조변수의 형에 따라서 접근하는 변수가 결정된다.
        System.out.println();

        ca1.showCakeSize();
        System.out.println();
        ca2.showCakeSize();
    }
}

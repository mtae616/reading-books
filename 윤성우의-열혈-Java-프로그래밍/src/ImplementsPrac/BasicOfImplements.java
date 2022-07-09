package ImplementsPrac;

class SuperCLS {
    public SuperCLS() {
        System.out.println("I'm Super Class");
    }
    public SuperCLS(int i) {
        System.out.println("Con : Super Class(int i)");
    }
    public SuperCLS(int i, int j) {
        System.out.println("Con : Super Class(int i, int j)");
    }
}
class SubCLS extends SuperCLS {
    public SubCLS() {
        // super(); 얘 주석 풀어도 super class 호출 1번
        // super(1); 혹은 생성자 지정 호출
        // super(1, 2); 생성자 지정 호출
        System.out.println("I'm Sub Class");
//        super(1); 여기 있으면 컴파일 오류
    }
}

public class BasicOfImplements {
    public static void main(String[] args) {
        // 연관된 일련의 클래스들에 대해 공통적인 규약을 정의할 수 있다.
        // 상속은 코드의 재활용을 목적으로 사용하는 문법이 아니다.
        // 하위 클래스 인스턴스 생성 시 상위 클래스, 하위 클래스의 생성자 모두 호출된다.
        // 하위 클래스의 인스턴스 생성 시 상위 클래스의 생성자가 먼저 호출된다.
        new SubCLS();
    }
}

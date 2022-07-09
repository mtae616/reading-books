package ClassVariableAndMethod;

class InstCnt {
    // static으로 선언된 변수는 변수가 선언된 클래스의 모든 인스턴스가 공유하는 변수이다.
    static int instNum = 0;

    InstCnt() {
        instNum++; // 클래스 내부에서 이름으 통한 접근
        System.out.println("인스턴스 생성 : " + instNum);
    }
}

public class StaticVariable {
    public static void main(String[] args) {
        InstCnt cnt1 = new InstCnt();
        InstCnt cnt2 = new InstCnt();
        InstCnt cnt3 = new InstCnt();
        cnt3.instNum++;
        System.out.println(cnt3.instNum);
        InstCnt.instNum++;
        // 객체 없이 class로도 접근 가능
        // 클래스 변수는 인스턴스 생성 이전에 메모리 공간에 존재한다.
        System.out.println(InstCnt.instNum);
    }
}

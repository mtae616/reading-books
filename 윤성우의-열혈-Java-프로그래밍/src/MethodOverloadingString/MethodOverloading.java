package MethodOverloadingString;

class Person {
    private int regiNum;
    private int passNum;

    public Person(int regiNum, int passNum) {
        this.regiNum = regiNum;
        this.passNum = passNum;
    }

    public Person(int regiNum) {
//        this.regiNum = regiNum;
//        this.passNum = 0;
        this(regiNum, 0); // 위와 동일한 표현
    }
    void showPersonalInfo() {
        System.out.println("주민등록 번호: " + regiNum);
        if (passNum != 0)
            System.out.println("여권 번호: " + passNum + '\n');
        else
            System.out.println("여권을 가지고 있지 않습니다. '\n");
    }
}

public class MethodOverloading {
    public static void main(String[] args) {
        // 메소드 이름이 같아도 매개변수 선언이 다르면 메소드 호출문의 전달인자를 통해서 호출된 메소드를 구분할 수 있다.
        // 매개변수 선언이 다르면 동일한 이름의 메소드 정의를 허용하는데 이를 '메소드 오버로딩' 이라고 한다.
        // 매개변수는 수나 type이 달라야 한다.
        // 반환형이 다르면 메소드 오버로딩이 성립하지 않는다.

        // 생성자도 오버로딩이 가능하다.
        Person jung = new Person(123123, 123123);
        Person park = new Person(2234234);

        jung.showPersonalInfo();
        park.showPersonalInfo();
    }
}

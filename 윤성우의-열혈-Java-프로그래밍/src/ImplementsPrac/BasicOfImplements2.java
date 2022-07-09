package ImplementsPrac;

class Man {
    String name;

    public Man(String name) {
        this.name = name;
    }
    public void tellYourName() {
        System.out.println("My name is " + name);
    }
}

class BuisnessMan extends Man {
    String company;
    String position;

    public BuisnessMan(String name, String company, String position) {
        super(name); // 상위 클래스 생성자 호출
        this.company = company;
        this.position = position;
    }
    public void tellYourInfo() {
        System.out.println("My company is " + company);
        System.out.println("My Position is " + position);
        tellYourName();
    }
}

public class BasicOfImplements2 {
    public static void main(String[] args) {
        // 단일 상속만 가능하다.
        // buisnessman class는 man class 로만 상속받을 수 있음!ㅁ
        BuisnessMan man = new BuisnessMan("SOO", "naver", "SW eng");
        man.tellYourInfo();
    }
}

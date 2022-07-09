package EnumAndVariadicAndAnnotation;

enum Animal {
    DOG, CAT
}

//enum Person {
//    MAN, WOMAN
//}

public class SimpleEnum {
    public static void who(Person man) {
        switch (man) {
            case MAN:
                System.out.println("남성 손닙입니다.");
                break;
            case WOMAN:
                System.out.println("여성 손닙입니다.");
                break;
        }
    }
    public static void main(String[] args) {
        who(Person.MAN);
//        who(Animal.DOG); 비정상적 호출
    }
}

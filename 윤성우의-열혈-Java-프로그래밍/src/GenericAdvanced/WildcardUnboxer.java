package GenericAdvanced;

class Unboxer {
    public static <T> T openBox(Box<T> box) { return box.getOb(); }
    public static <T> void peekBox(Box<T> box) {
        System.out.println(box);
    }

    public static void peekBox2(Box<? super Integer> box) {
        // 비교하면 조금 더 단순함, T가 Integer의 super class, T 는 Integer 또는 Integer가 상속하는 클래스여야 함
        System.out.println(box);
    }
}

public class WildcardUnboxer {
    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.setOb("So Simple String");
        Unboxer.peekBox(box); // 상자 안의 내용물을 확인해본다.
    }
}

package GenericPrac;

class Boxes<T> {
    private T ob;

    public T getOb() {
        return ob;
    }

    public void setOb(T ob) {
        this.ob = ob;
    }
}

class BoxesFactory {
    public static <T extends Number> Boxes<T> makeBoxes(T o) { // 제네릭 메소드 정의
        Boxes<T> box = new Boxes<T>(); // 상자를 생성하고,
        box.setOb(o); // 전달된 인스턴스를 상자에 담아서
        return box; // 전달!
    }
}

class UnBoxer {
    public static <T extends Number> T openBox(Boxes<T> box) { // extends 설정하면서 리턴값 제한
        return box.getOb();
    }
}

public class GenericMethodClassMaker {
    public static void main(String[] args) {
//        Boxes<String> sBox = BoxesFactory.makeBoxes("Sweet");
//        System.out.println(sBox.getOb());
//        System.out.println(UnBoxer.openBox(sBox));

        System.out.println();

        Boxes<Double> dBox = BoxesFactory.makeBoxes(7.59);
        System.out.println(dBox.getOb());
        System.out.println(UnBoxer.openBox(dBox));
    }
}

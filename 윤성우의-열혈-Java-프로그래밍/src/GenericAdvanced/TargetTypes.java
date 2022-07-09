package GenericAdvanced;

class EmptyBoxFactory {
    public static <T> Box<T> makeBox() {
        Box<T> box = new Box<T>();
        return box;
    }
}

public class TargetTypes {

    public static void main(String[] args) {
        Box<Integer> iBox = EmptyBoxFactory.makeBox();
        // == EmptyBoxFactory.<Integer>makeBox(); 타입 추론으로 제너릭 생략 가능
        iBox.setOb(25);
        System.out.println(iBox.getOb());
    }
}

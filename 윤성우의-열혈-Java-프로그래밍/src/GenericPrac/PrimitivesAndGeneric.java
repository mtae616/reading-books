package GenericPrac;

class Box2<T> {
    private T ob;

    public T getOb() {
        return ob;
    }

    public void setOb(T ob) {
        this.ob = ob;
    }
}

public class PrimitivesAndGeneric {
    public static void main(String[] args) {
        Box2<Integer> iBox = new Box2<Integer>();
        iBox.setOb(125); // 오토 박싱
        int num = iBox.getOb(); // 오토 언박싱
        System.out.println(num);
    }
}

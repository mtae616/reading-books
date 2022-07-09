package GenericPrac;

class BBox<T extends Number> {
    private T ob;

    public T getOb() {
        return ob;
    }

    public void setOb(T ob) {
        this.ob = ob;
    }

    public int toIntValue() { // 가능한 이유는 extends로 Number 받아서
        return ob.intValue();
    }

}

public class BoundedBox {
    public static void main(String[] args) {
        BBox<Integer> iBox = new BBox<>();
        iBox.setOb(24);

        BBox<Double> dBox = new BBox<>();
        dBox.setOb(5.97);

    }
}

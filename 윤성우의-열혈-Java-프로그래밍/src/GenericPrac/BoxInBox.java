package GenericPrac;

class Box3<T> {
    private T ob;

    public T get() {
        return ob;
    }

    public void set(T ob) {
        this.ob = ob;
    }
}

public class BoxInBox {
    public static void main(String[] args) {
        Box3<String> sBox = new Box3<>();
        sBox.set("I am so happy");
        Box3<Box3<String>> wBox = new Box3<>();
        wBox.set(sBox);
        System.out.println(wBox.get() == sBox);
        Box3<Box3<Box3<String>>> zBox = new Box3<>();
        zBox.set(wBox);
        System.out.println(zBox.get().get() == sBox);
    }
}

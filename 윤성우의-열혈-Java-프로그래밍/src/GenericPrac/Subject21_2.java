package GenericPrac;

class BBBox<T> {
    private T ob;

    public T getOb() {
        return ob;
    }

    public void setOb(T ob) {
        this.ob = ob;
    }
}

public class Subject21_2 {
    public static <T extends Number> void swap(BBBox<T> o, BBBox<T> b) {
        T temp = o.getOb();
        o.setOb(b.getOb());
        b.setOb(temp);
    }

    public static void main(String[] args) {
        BBBox<Integer> box1 = new BBBox<>();
        box1.setOb(99);

        BBBox<Integer> box2 = new BBBox<>();
        box2.setOb(55);

        swap(box1, box2);

        System.out.println(box1.getOb() + "\n" + box2.getOb());
    }
}

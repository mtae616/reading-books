package LambdaExpression;

import java.util.function.BiConsumer;

class Box<T> {
    private T ob;

    public T getOb() {
        return ob;
    }

    public void setOb(T ob) {
        this.ob = ob;
    }
    @Override
    public String toString()
    { return ob.toString(); }

}

public class Subject27_4 {
    public static void main(String[] args) {
        BiConsumer<Box<Integer>, Integer> c1 = (b, i) -> b.setOb(i);
        BiConsumer<Box<Double>, Double> c2 = (b, i) -> b.setOb(i);

        Box<Integer> b1 = new Box<>();
        Box<Double> b2 = new Box<>();

        c1.accept(b1, 10);
        c2.accept(b2, 5.59);

        System.out.println(b1.getOb());
        System.out.println(b2.getOb());
    }
}

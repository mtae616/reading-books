package GenericAdvanced;

class BoundedWildcardDemo {
    public static void addBox(Box<? extends Integer> b1, Box<? extends Integer> b2, Box<? super Integer> b3) {
        b3.setOb(b1.getOb() + b2.getOb());
    }
}

public class Subject22_1 {
    public static void main(String[] args) {
        Box<Integer> box1 = new Box<>();
        box1.setOb(24);

        Box<Integer> box2 = new Box<>();
        box2.setOb(37);

        Box<Integer> result = new Box<>();
        result.setOb(0);
        BoundedWildcardDemo.addBox(box1, box2, result);
        System.out.println(result.getOb());
    }
}

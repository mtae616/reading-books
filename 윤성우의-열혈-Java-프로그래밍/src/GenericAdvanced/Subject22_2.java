package GenericAdvanced;

public class Subject22_2 {
    public static <T> boolean compBox(Box<T> box, Integer n) {
        Integer bc = (Integer) box.getOb();
        box.setOb((T) n);
        return bc.equals(n);
    }
    public static <T> boolean compBox(Box<T> box, String s) {
        String bc = (String) box.getOb();
        box.setOb((T) s);
        return bc.equals(s);
    }

    public static void main(String[] args) {
        Box<Integer> box1 = new Box<>();
        box1.setOb(24);

        Box<String> box2 = new Box<>();
        box2.setOb("Poly");

        if (compBox(box1, 25))
            System.out.println("상자 안에 25!");
        if(compBox(box2, "Moly"))
            System.out.println("상자 안에 Moly");
        System.out.println(box1.getOb());
        System.out.println(box2.getOb());
    }
}

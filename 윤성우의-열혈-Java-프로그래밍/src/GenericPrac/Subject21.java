package GenericPrac;

class DDBox<L, R> {
    private L left;
    private R right;

    public void set(L o, R r) {
        this.left = o;
        this.right = r;
    }
    public String toString() { return left + " & " + right + "\n"; }
}

public class Subject21 {
    public static void main(String[] args) {
        DDBox<String, Integer> box1 = new DDBox<>();
        box1.set("Apple", 25);
        DDBox<String, Integer> box2 = new DDBox<>();
        box2.set("Orange", 33);

        DDBox<DDBox<String, Integer>, DDBox<String, Integer>> ddbox = new DDBox<>();
        ddbox.set(box1, box2);

        System.out.print(ddbox);
    }
}

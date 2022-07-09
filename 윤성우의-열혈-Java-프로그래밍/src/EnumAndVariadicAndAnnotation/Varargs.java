package EnumAndVariadicAndAnnotation;

public class Varargs {
    public static void showAll(String...vargs) {
        System.out.println("LEN: " + vargs.length);

        for(String s : vargs)
            System.out.println(s);
    }

    public static void showAll2(String[] vargs) {
        System.out.println("LEN: " + vargs.length);

        for(String s : vargs)
            System.out.println(s);
    }

    public static void main(String[] args) {
        showAll("BOX");
        System.out.println();
        showAll("BOX", "TOY");
        System.out.println();
        showAll("BOX", "TOY", "APPLE");

        System.out.println();

        showAll2(new String[]{"BOX"});
        System.out.println();
        showAll2(new String[]{"BOX", "TOY"});
        System.out.println();
        showAll2(new String[]{"BOX", "TOY", "APPLE"});
    }
}

package NestedClassAndLambda;

interface Printable2 {
    public void print(String s);
}


public class Lambda2 {
    public static void main(String[] args) {
        Printable prn = new Printable() {
            @Override
            public void print() {}
            @Override
            public void print(String s) {
                System.out.println(s);
            }
        };
        prn.print("what is lambda?");

        Printable2 prn2 = (s) -> System.out.println(s);
        prn2.print("that is lambda");
    }
}

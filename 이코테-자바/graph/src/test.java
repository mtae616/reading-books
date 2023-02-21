interface testing {
    default String defaultMethod() {
        return "hi!";
    }
}

public class test implements testing{
    public static void main(String[] args) {
        test test = new test();
        System.out.println(test.defaultMethod());
    }
}

package LambdaExpression;

@FunctionalInterface
interface Calculate3<T> {
    T cal(T a, T b);
}

public class Subject72_1 {
    public static void main(String[] args) {
        Calculate3<Integer> ci = (a, b) -> a + b;
        System.out.println(ci.cal(3, 4));
        Calculate3<Double> cd = (a, b) -> a + b;
        System.out.println(cd.cal(2.5, 7.1));
        Calculate3<Integer> ci2 = (a, b) -> a - b;
        System.out.println(ci2.cal(4, 2));
        Calculate3<Double> cd2 = (a, b) -> a - b;
        System.out.println(cd2.cal(4.9, 3.2));
    }
}

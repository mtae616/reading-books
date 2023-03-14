public class ch05_recursion_test {

    public static int power(int n, int m) {
        if (n == 0 || m == 0) return 1;
        return n * power(n, m - 1);
    }

    public static void main(String[] args) {
        System.out.println(power(2, 5));
    }
}

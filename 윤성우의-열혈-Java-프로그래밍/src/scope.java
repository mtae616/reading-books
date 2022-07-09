public class scope {
    public static int power(int n) {
        if (n == 0)
            return 1;
        return 2 * power(n - 1);
    }

    public static void bin(int n) {
        if(n > 0) {
            int bin = n % 2;
            bin(n / 2);
            System.out.print(bin);
        }
    }

    public static void main(String_prac[] args) {
        int res = power(5);
        System.out.println(res);
        bin(10);
    }

}

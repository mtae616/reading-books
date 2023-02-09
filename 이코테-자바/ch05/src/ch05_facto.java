public class ch05_facto {

    static int[] memo = new int[10000];

    public static int facto_iter(int n){
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static int facto_recur(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * facto_iter(n - 1);
    }

    public static int facto_memo(int n) {
        if (memo[n] != 0) return memo[n];
        return n * facto_memo(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(facto_iter(5));
        System.out.println(facto_recur(5));

        memo[0] = 1;
        memo[1] = 1;
        System.out.println(facto_memo(5));
    }
}

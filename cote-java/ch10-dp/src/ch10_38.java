class Solution {
    public static int[] arr = new int[100001];

    static {
        arr[1] = 1;
        arr[2] = 1;
    }

    public static int solution(int n) {
        if (arr[n] != 0) return arr[n];
        return arr[n] = (solution(n - 2) + solution(n - 1)) % 1234567;
    }
}

//class Solution {
//    public static int[] arr = new int[100001];
//
//    static {
//        arr[1] = 1;
//        arr[2] = 1;
//    }
//
//    public static int solution(int n) {
//        for (int i = 3; i < n + 1; i++) {
//            arr[i] = ((arr[i - 1] + arr[i - 2]) % 1234567);
//        }
//        return arr[n];
//    }
//}

//import java.util.Arrays;
//
//class Solution {
//    public static int[] mem = new int[100001];
//
//    private static int fibonacci(int n) {
//        if (mem[n] != -1) return mem[n];
//        if (n == 0 || n == 1) return n;
//
//        return mem[n] = (fibonacci(n - 1) + fibonacci(n - 2)) % 1234567;
//    }
//
//    public static int solution(int n) {
//        Arrays.fill(mem, -1);
//        for (int i = 0; i <= n; i++) {
//            fibonacci(n);
//        }
//
//        return fibonacci(n);
//    }
//}

public class ch10_38 {
    public static void main(String[] args) {
        System.out.println(Solution.solution(3));
    }
}

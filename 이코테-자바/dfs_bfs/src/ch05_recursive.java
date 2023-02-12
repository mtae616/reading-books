public class ch05_recursive {

    public static void recursiveFunction() {
        System.out.println("재귀 함수를 호출합니다.");
        recursiveFunction();
    }

    public static void recursiveExitFunction(int i) {
        if (i == 100) return;
        System.out.println("the call is : " + i);
        recursiveExitFunction(i + 1);
    }

    public static void main(String[] args) {
        recursiveExitFunction(0);
    }
}

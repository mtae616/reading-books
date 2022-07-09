public class String_prac {
    public static void main(java.lang.String[] args) {
        // 문자열 선언과 동시에 참조변수로 참조한다.
        java.lang.String myName = "Kang Tae Soo";
        printString(myName);
        printString("hello!");
    }
    // String 참조변수를 매개변수로 선언하여 문자열을 전달 받을 수 있다.
    public static void printString(java.lang.String str) {
        System.out.println(str);
    }
}

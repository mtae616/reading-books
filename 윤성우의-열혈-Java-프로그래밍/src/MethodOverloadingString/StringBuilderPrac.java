package MethodOverloadingString;

public class StringBuilderPrac {
    public static void parser(String s)
    {
        StringBuilder b = new StringBuilder(s);
        b.delete(6,7);
        System.out.println(b.toString());
    }


    public static void main(String[] args) {
        // 내부적으로 문자열을 저장하기 위한 메모리 공간을 지닌다.
        // String 클래스와 달리 문자를 추가하거나 삭제하는 것이 가능하다.
        // StringBuffer와 StringBuilder는 사실상 같지만
        // StringBuffer는 속도가 느린 대신 쓰레드 safe하다

        StringBuilder b = new StringBuilder("123");

        b.append(123123); // 문자열 덧붙이기
        System.out.println(b);

        System.out.println(b.toString());

        System.out.println(b.delete(0, 2)); // 시작부터 end 이전까지 삭제

        System.out.println(b.replace(0, 3, "AB")); // start 부터 end 이전까지 대체

        System.out.println(b.reverse());

        String sub = b.substring(2, 4);
        System.out.println(sub);

        StringBuilder stb1 = new StringBuilder("123");
        StringBuilder stb2 = new StringBuilder(45678);

        parser("990925-1012999");
    }
}

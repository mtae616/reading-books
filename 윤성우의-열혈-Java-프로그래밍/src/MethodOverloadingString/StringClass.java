package MethodOverloadingString;

public class StringClass {
    public static void main(String[] args) {
        String str = new String("Simple String"); // str 이 참조변수
        System.out.println(str.length());

        String str1 = "Simple String";
        String str2 = "Simple String";

        String str3 = new String("Simple String");
        String str4 = new String("Simple String");

        // string class 는 immutable하다
        System.out.println(str1 == str2);
        // 동일 인스턴스 참조
        // 문자열 내용이 같이 때문에 하나의 인스턴스를 생성해서 공유하는 방식으로 처리
        // str2 = str1; 로 처리해도 됨
        System.out.println(str3 == str4);
        // 다른 인스턴스 참조
        str1 += "asd";
        // str1의 값이 바뀌는 것은 새로운 객체를 생성한 것이다.
    }
}

package MethodOverloadingString;

public class StringMethods {
    static void parser(String s)
    {
        String temp = "";
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '-')
                temp += s.charAt(i);
        }
        if (temp.length() != 13)
            return;
        else
            System.out.println(temp);
    }

    public static void main(String[] args) {
        String a = "asd";
        String b = "zxc";

        System.out.println(a.concat(b)); // 붙이기
        System.out.println(a.substring(1, 2)); // 추출, 인덱스값 부터, 범위 미포함해서

        String c = new String("asd");
        System.out.println(c == "asd"); // false
        System.out.println(c.equals("asd")); // true

        System.out.println(c.compareTo(a)); // 같으면 0, 다르면 c - a strcmp 랑 비슷한듯

        String d = "ASD";
        System.out.println(c.compareToIgnoreCase(d)); // 대소문자 구분 없이 compreTo

        double e = 2.718281;
        String s = String.valueOf(e); // 문자열로 변환
        System.out.println(s);
        System.out.println(s.getClass().getName());

        parser("990925-1012999");
    }
}

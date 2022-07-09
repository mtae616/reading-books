public class printf {
    public static void main(String_prac[] args) {
        int a = 12;

        System.out.println(a);
        System.out.println(12);
        System.out.println("12");
        System.out.println(""+ 1+2);
        System.out.println((6+6));
        double b = 20.32;
        int c = (int)b; // 형 변환하면서 소수점 버림
        System.out.println(c);

//        int num1 = 10;
//        int num2 = 20;
//        int num3 = 30;
//
//        num1 = num2 = num3;
//        System.out.println(num1 + " " + num2 + " " + num3);

        int num1 = 0;
        int num2 = 0;
        boolean result;

        result = ((num1 += 10) > 0) && ((num2 += 10) > 0);
        System.out.println(result);
        System.out.println(num1);
        System.out.println(num2);

        System.out.println(((25 * 5) + (36 - 4) - 72) / 5);

    }
}

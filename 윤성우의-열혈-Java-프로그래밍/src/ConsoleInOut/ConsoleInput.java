package ConsoleInOut;

import java.util.Scanner;

public class ConsoleInput {
    public static void main(String[] args) {
        String src = "1 2 3";
        Scanner sc = new Scanner(src); // 데이터를 추출하는 기능
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        Scanner s = new Scanner(System.in);
        // System.in 은 키보드를 의미하는 인스턴스의 참조변수이다.
        String str1 = s.nextLine();
        String str2 = s.nextLine();
        System.out.println(str1);
        System.out.println(str2);
    }
}

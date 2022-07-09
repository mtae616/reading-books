package PrimitiveClass;

import java.util.Random;
import java.util.Scanner;

public class Subject20_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("정수 입력");
        int num1 = sc.nextInt();
        System.out.print("정수2 입력");
        int num2 = sc.nextInt();

        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int min = num1;
            min += rand.nextInt(num2 - num1 + 1);
            System.out.println(min);
        }
    }
}

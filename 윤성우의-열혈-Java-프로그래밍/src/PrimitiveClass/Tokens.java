package PrimitiveClass;

import java.util.StringTokenizer;

public class Tokens {
    public static void main(String[] args) {
        StringTokenizer st1 = new StringTokenizer("PM:08:45", ":");

        while (st1.hasMoreTokens()) {
            System.out.println(st1.nextToken());
        }
        StringTokenizer st2 = new StringTokenizer("12 + 36 - 8 / 2 = 44", "+-/= ");
        while (st2.hasMoreTokens()) { // 토큰이 남아있다면
            System.out.println(st2.nextToken()); // 토큰 반환
        }

    }
}

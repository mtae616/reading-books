package PrimitiveClass;

import java.util.Random;

public class MathClassRandNum {
    public static void main(String[] args) {
        // math 클래스의 모든 메소드는 static
        // math는 기능 목적, 인스턴스 생성 목적은 아님
        System.out.println(Math.PI); // 원주율
        System.out.println(Math.sqrt(2)); // 제곱근
        System.out.println();

        System.out.println(Math.toDegrees(Math.PI)); // 파이에 대한 degree
        System.out.println(Math.toDegrees(2.0 * Math.PI)); // 2 파이에 대한 degree
        System.out.println();

        double radian45 = Math.toRadians(45); // 라디안으로 변환
        System.out.println(Math.sin(radian45)); // 싸인 45
        System.out.println(Math.cos(radian45)); // 코싸인 45
        System.out.println(Math.tan(radian45)); // 탄젠트 45
        System.out.println();

        System.out.println(Math.log(25)); //로그 25
        System.out.println(Math.pow(2, 16)); // 2의 16승
        System.out.println();

        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            System.out.println(rand.nextInt(1000)); // 0 이상 1000 미만 난수 생성
        }
        System.out.println();
        Random rand2 = new Random(12);
        // 생성자로 씨드 값(seed number) 전달
        // 씨드값이 같으면 난수 패턴은 같다
        for (int i = 0; i < 7; i++) {
            System.out.println(rand2.nextInt(1000));
        }
        System.out.println();
        Random rand3 = new Random(System.currentTimeMillis());
        for (int i = 0; i < 7; i++) {
            System.out.println(rand.nextInt(1000));
        }

    }
}

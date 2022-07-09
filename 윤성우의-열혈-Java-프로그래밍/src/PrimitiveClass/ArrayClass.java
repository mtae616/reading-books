package PrimitiveClass;

import java.util.Arrays;

public class ArrayClass {
    public static void main(String[] args) {
        double[] arOrg = {1.1, 2.2, 3.3, 4.4, 5.5};

        // 배열 전체를 복사
        double[] arCpy1 = Arrays.copyOf(arOrg, arOrg.length);

        // 세번째 요소까지만 복사
        double[] arCpy2 = Arrays.copyOf(arOrg, 3);

        // 배열 arOrg의 인덱스 1에서 배열 cpy 인덱스 0으로 세 개의 요소 복사
        double[] cpy = new double[3];
        System.arraycopy(arOrg, 1, cpy, 0, 3);

        for(double d: arCpy1)
            System.out.println(d);
        System.out.println();
        for(double d: arCpy2)
            System.out.println(d);
        System.out.println();
        for (double d : cpy)
            System.out.println(d);
    }
}

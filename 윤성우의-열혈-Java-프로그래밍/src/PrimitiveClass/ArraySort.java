package PrimitiveClass;

import java.util.Arrays;

public class ArraySort {
    public static void main(String[] args) {
        int[] ar1 = {1, 5, 3, 2, 4};
        double[] ar2 = {3.3, 2.2, 5.5, 1.1, 4.4};
        Arrays.sort(ar1);
        Arrays.sort(ar2);

        for(int n : ar1)
            System.out.println(n);
        System.out.println();
        for(double n : ar2)
            System.out.println(n);
    }
}

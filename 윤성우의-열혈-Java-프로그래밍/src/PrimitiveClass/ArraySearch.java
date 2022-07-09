package PrimitiveClass;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArraySearch {
    public static void main(String[] args) {
        int[] ar = {33, 55, 11, 44, 22};
        Arrays.sort(ar);
        for (int n : ar) {
            System.out.println(n);
        }
        System.out.println();

        int idx = Arrays.binarySearch(ar, 33);
        System.out.println("Index of 33: " + idx); // 배열 ar에서 33을 찾아라

        Person[] arr = new Person[3];
        arr[0] = new Person("Lee", 29);
        arr[1] = new Person("Goo", 15);
        arr[2] = new Person("Soo", 37);
        Arrays.sort(arr);

        int idx2 = Arrays.binarySearch(arr, new Person("who are you?", 37)); // soo 나옴, 37이란 값이 같아서...
        System.out.println(arr[idx2]);
    }
}

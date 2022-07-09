package PrimitiveClass;

import java.util.Arrays;

public class Subject20_2 {
    public static void main(String[] args) {
        Person[] ar = new Person[3];
        ar[0] = new Person("Le", 29);
        ar[1] = new Person("Gooddd", 15);
        ar[2] = new Person("Sood", 37);

        Arrays.sort(ar);
        for (Person p : ar) {
            System.out.println(p);
        }
    }
}


package PrimitiveClass;

import java.util.Arrays;

class Person implements Comparable {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        // 인터페이스로 존재하는 compareTo 오버라이드
        // sort 내부에서 compareTo를 활용해서 정렬한다.
        Person p = (Person) o;
//        if (this.name.length() > p.name.length())
//            return 1; // o 가 작으면 양수
//        else if(this.name.length() < p.name.length())
//            return 1; // o 가 크면 음수
//        else
//            return 0; // 같으면 0
//        return this.name.length() - p.name.length();
        return this.age - p.age;
    }

    @Override
    public String toString() {
        return name + ": " + age;
    }
}

public class ArrayObjSort {
    public static void main(String[] args) {
        Person[] ar = new Person[3];
        ar[0] = new Person("Lee", 29);
        ar[1] = new Person("Goo", 15);
        ar[2] = new Person("Soo", 37);

        Arrays.sort(ar);
        for (Person p : ar) {
            System.out.println(p);
        }
    }
}

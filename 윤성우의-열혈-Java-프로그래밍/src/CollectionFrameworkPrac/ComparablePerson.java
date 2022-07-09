package CollectionFrameworkPrac;

import com.sun.source.tree.Tree;

import java.util.Comparator;
import java.util.TreeSet;

class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return name + " " + age;}

    // 나이 기준 정
    @Override
    public int compareTo(Person p) {
        return this.age - p.age;
    }
}

class PersonComparatorPerson implements Comparator<Person> {
    public int compare(Person p1, Person p2){ // compare override 해서 정렬 순서 고칠 수 있음
        return p2.age - p1.age;
    }
}

public class ComparablePerson {
    public static void main(String[] args) {
        TreeSet<Person> tree = new TreeSet<>(new PersonComparatorPerson());
        // TreeSet(Comparator<? super E> comparator
        tree.add(new Person("Yoon", 37));
        tree.add(new Person("HONG", 53));
        tree.add(new Person("Park", 22));

        for(Person p : tree)
            System.out.println(p);
    }
}

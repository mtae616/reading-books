//package CollectionFrameworkPrac;
//
//import java.util.HashSet;
//import java.util.Objects;
//
//class Person {
//    private String name;
//    private int age;
//
//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//    @Override
//    public String toString() {
//        return name + "(" + age + "세)";
//    }
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, age);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        String n = ((Person)obj).name;
//        int a = ((Person)obj).age;
//
//        if (name.equals(n) && a == age)
//            return true;
//        else
//            return false;
//
//    }
//
//}
//
//public class Subject23_1 {
//    public static void main(String[] args) {
//        HashSet<Person> set = new HashSet<>();
//        set.add(new Person("hey", 11));
//        set.add(new Person("hey", 13));
//        set.add(new Person("hey", 11));
//        set.add(new Person("hey", 13));
//        set.add(new Person("lol", 14));
//
//        for(Person p : set)
//            System.out.println(p);
//    }
//}

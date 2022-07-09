package CollectionFrameworkPrac;

import java.util.HashSet;

public class HashSetEqualityOne {
    public static void main(String[] args) {
        HashSet<Num> set = new HashSet<>();
        set.add(new Num(7799));
        set.add(new Num(9955));
        set.add(new Num(7799));
        // 인스턴스가 다르면 Object 클래스의 hashCode 메소드는 다른 값을 반환한다.
        // 인스턴스가 다르면 Object 클래스의 equals 메소드는 false를 반환한다.
        System.out.println("인스턴스 수: " + set.size());

        for(Num n : set)
            System.out.println(n);
    }
}

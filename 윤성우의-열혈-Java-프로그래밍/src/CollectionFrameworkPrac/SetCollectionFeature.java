package CollectionFrameworkPrac;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetCollectionFeature {
    public static void main(String[] args) {
        // set : 순서 보장 X, 반복 허용 X
        Set<String> set = new HashSet<>();
        set.add("Toy");
        set.add("Box");
        set.add("Robot");
        set.add("Box");
        System.out.println("인스턴스 수: " + set.size());
        for(Iterator<String> itr = set.iterator(); itr.hasNext();)
            System.out.println(itr.next());

        System.out.println();

        for(String s: set)
            System.out.println(s);
    }
}

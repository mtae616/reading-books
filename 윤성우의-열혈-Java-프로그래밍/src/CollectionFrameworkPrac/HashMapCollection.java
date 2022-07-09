package CollectionFrameworkPrac;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapCollection {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        // Key-value 기반 데이터 저장
        map.put(45, "Brown");
        map.put(37, "James");
        map.put(23, "Martin");

        System.out.println("23 번 : " + map.get(23));
        System.out.println("37 번 : " + map.get(37));
        System.out.println("23 번 : " + map.get(23));
        System.out.println();

//        map.remove(37);
//
//        System.out.println("37 번 : " + map.get(37));

        Set<Integer> ks = map.keySet();
        for(Integer n : ks)
            System.out.println(n.toString());
        System.out.println();

        for(Integer n: ks)
            System.out.println(map.get(n));

        System.out.println();

        for(Iterator<Integer> itr = ks.iterator(); itr.hasNext();)
            System.out.println(map.get(itr.next()));
        System.out.println();
        for(Integer n : ks)
            System.out.println(n + map.get(n));
    }
}

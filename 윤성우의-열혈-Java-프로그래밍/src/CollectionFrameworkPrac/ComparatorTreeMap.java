package CollectionFrameworkPrac;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Comparator;

class AgeComparator implements Comparator<Integer> { // 정렬 반대로
    public int compare(Integer n1, Integer n2) {
        return n2.intValue() - n1.intValue(); 
    }
}

class ComparatorTreeMap {
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>(new AgeComparator());

        map.put(45, "Brown");
        map.put(37, "James");
        map.put(23, "Martin");

        Set<Integer> ks = map.keySet();

        for(Integer n : ks)
            System.out.print(n.toString() + '\t');
        System.out.println();

        for(Integer n : ks)
            System.out.print(map.get(n).toString() + '\t');
        System.out.println();

        for(Iterator<Integer> itr = ks.iterator(); itr.hasNext(); )
            System.out.print(map.get(itr.next()) + '\t');
        System.out.println();
    }
}
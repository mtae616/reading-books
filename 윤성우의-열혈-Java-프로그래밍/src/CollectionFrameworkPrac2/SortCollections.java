package CollectionFrameworkPrac2;

import java.util.*;

public class SortCollections {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Box", "Robot", "Weapon");
        list = new ArrayList<>(list);

        for(Iterator<String> itr = list.iterator(); itr.hasNext();)
            System.out.println(itr.next());
        System.out.println();
        Collections.sort(list);
        for(Iterator<String> itr = list.iterator(); itr.hasNext();)
            System.out.println(itr.next());
        System.out.println();
    }
}

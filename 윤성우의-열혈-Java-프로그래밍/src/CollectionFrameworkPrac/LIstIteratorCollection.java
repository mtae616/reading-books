package CollectionFrameworkPrac;

import java.util.*;

public class LIstIteratorCollection {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Box", "Robot", "Box");
        list = new ArrayList<>(list);

        ListIterator<String> litr = list.listIterator(); // 양방향 반복자 획득
        String str;
        while(litr.hasNext()) { // 왼 -> 오
            str = litr.next();
            System.out.println(str);
            if (str.equals("Toy"))
                litr.add("Toy2");
        }
        System.out.println();

        while (litr.hasPrevious()) { // 오 -> 왼
            str = litr.previous();
            System.out.println(str);
            if (str.equals("Robot"))
                litr.add("Robot2");
        }

        System.out.println();

        for(Iterator<String> itr = list.iterator(); itr.hasNext();)
            System.out.println(itr.next());
    }
}

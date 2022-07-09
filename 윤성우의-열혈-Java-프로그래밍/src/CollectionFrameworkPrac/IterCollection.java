package CollectionFrameworkPrac;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IterCollection {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("Toy");
        list.add("Box");
        list.add("Robot");
        list.add("Box");

        Iterator<String> itr = list.iterator(); // 반복자 획득

        while(itr.hasNext())
            System.out.println(itr.next());
        System.out.println();

        itr = list.iterator(); // 반복자 다시 획득
        while (itr.hasNext())
        {
            String str = itr.next();
            if (str.equals("Box"))
                itr.remove(); // Box 지움
        }

        itr = list.iterator();
        while(itr.hasNext())
            System.out.println(itr.next());
    }
}

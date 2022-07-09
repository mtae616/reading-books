package CollectionFrameworkPrac;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListCollection {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
        List<String> list = new LinkedList<>();
        list.add("Toy");
        list.add("Box");
        list.add("Robot");

        for(int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
        System.out.println();

        for (String s : list)
            System.out.println(s);

        list.remove(0);

        System.out.println();

        for(int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));

        System.out.println();

        for (String s : list)
            System.out.println(s);
    }
}

package CollectionFrameworkPrac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ConvertCollection {
    public static void main(String[] args) {
        List<String> lst = Arrays.asList("Box", "Toy", "Box", "Toy");
        ArrayList<String> list = new ArrayList<>(lst);
        for(String s : list)
            System.out.println(s);

        HashSet<String> set = new HashSet<>(list);
        list = new ArrayList<>(set);
        System.out.println();
        for(String s : list)
            System.out.println(s);
    }
}

package Streams2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectStringStream {
    public static void main(String[] args) {
        String[] words = {"Hello", "Box", "Robot", "Toy"};
        Stream<String> ss = Arrays.stream(words);
        List<String> ls = ss.filter(s -> s.length() < 5).collect(() -> new ArrayList<>(), (c, s) -> c.add(s), (lst1, lst2) -> lst1.add(String.valueOf(lst2)));
        System.out.println(ls);
    }
}

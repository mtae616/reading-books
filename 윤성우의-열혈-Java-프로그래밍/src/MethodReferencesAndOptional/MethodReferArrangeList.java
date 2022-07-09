package MethodReferencesAndOptional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class MethodReferArrangeList {
    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9);
        ls = new ArrayList<>(ls);
//        Collections.reverse(ls);
        // Consumer<List<Integer>> c = l -> Collections.reverse(l); 아래 거랑 같은 기능
        Consumer<List<Integer>> c = Collections::reverse; // 메소드 참조
        c.accept(ls);
        System.out.println(ls);
    }
}

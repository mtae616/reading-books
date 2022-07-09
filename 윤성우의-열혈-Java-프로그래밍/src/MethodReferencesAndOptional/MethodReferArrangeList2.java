package MethodReferencesAndOptional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

class JustSort {
    public void sort(List<?> ls) {
        Collections.reverse(ls);
    }

}

public class MethodReferArrangeList2 {
    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(1, 3, 5, 7, 9);
        ls = new ArrayList<>(ls);

        JustSort js = new JustSort();
//        js = new JustSort(); 컴파일 에러... 참조변수는 final 이거나, effectively final 이야 하기 때문에
//        Consumer<List<Integer>> c = e -> js.sort(e);
        Consumer<List<Integer>> c = js::sort;

        c.accept(ls);
        System.out.println(ls);

    }
}

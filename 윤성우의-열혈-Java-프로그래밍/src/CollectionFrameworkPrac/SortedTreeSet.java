package CollectionFrameworkPrac;

import com.sun.source.tree.Tree;

import java.util.Iterator;
import java.util.TreeSet;

public class SortedTreeSet {
    public static void main(String[] args) {
        TreeSet<Integer> tree = new TreeSet<>();
        tree.add(3);
        tree.add(2);
        tree.add(1);
        tree.add(4);
        System.out.println(tree.size());

        // 디폴트로 정렬
        for(Integer n : tree)
            System.out.println(n);
        System.out.println();
        for(Iterator<Integer> itr = tree.iterator(); itr.hasNext();)
            System.out.println(itr.next());
    }
}

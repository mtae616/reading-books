package CollectionFrameworkPrac;

import java.util.Deque;
import java.util.ArrayDeque;

class ArrayDequeCollection {
    public static void main(String[] args) {
        Deque<String> deq = new ArrayDeque<>();
        // stack 은 thread safe 하지만 성능 저하가 있음
        // deque 사용 추천됨

        // 앞에서 넣고
        deq.offerFirst("1.Box");
        deq.offerFirst("2.Toy");
        deq.offerFirst("3.Robot");

        // 앞에서 pop
        System.out.println(deq.pollFirst());
        System.out.println(deq.pollFirst());
        System.out.println(deq.pollFirst());  
    }
}
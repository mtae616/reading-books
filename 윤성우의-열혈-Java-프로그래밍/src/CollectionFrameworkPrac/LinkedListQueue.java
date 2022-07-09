package CollectionFrameworkPrac;

import java.util.Queue;
import java.util.LinkedList;

class LinkedListQueue {
    public static void main(String[] args) {
        Queue<String> que = new LinkedList<>();

        que.offer("Box");
        que.offer("Toy");
        que.offer("Robot");

        System.out.println("next: " + que.peek());

        // pop
        System.out.println(que.poll());
        System.out.println(que.poll());

        System.out.println("next: " + que.peek());
        // pop
        System.out.println(que.poll());
    }
}
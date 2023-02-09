import java.util.LinkedList;

public class ch05_queue {
    public static void main(String[] args) {
        LinkedList<Integer> q = new LinkedList<>();

        q.offer(5);
        q.offer(2);
        q.offer(3);
        q.offer(7);
        q.poll();
        q.offer(1);
        q.offer(4);
        q.poll();

        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }

    }
}

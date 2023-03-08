import java.io.*;
import java.util.PriorityQueue;

class qEl implements Comparable<qEl> {
    int value;

    public qEl(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(qEl o) {
        if (this.value < o.value) return -1;
        return 1;
    }
}

public class ch14_26 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<qEl> q = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            q.offer(new qEl(Integer.parseInt(br.readLine())));
        }

        int sum = 0;
        while(q.size() >= 2) {
            qEl a = q.poll();
            qEl b = q.poll();
            int sumValue = a.getValue() + b.getValue();
            sum += sumValue;
            q.offer(new qEl(sumValue));
        }

        bw.write(String.valueOf(sum));

        bw.flush();
        bw.close();
        br.close();
    }
}

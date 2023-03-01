import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Node implements Comparable<Node>{
    int index;
    double value;

    public Node(int index, float value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Node o) {
        if (this.value == o.value && this.index > o.index) return -1;
        else if (this.value < o.value) return -1;
        return 1;
    }
}

public class ch14_25 {
    public static int[] solution(int N, int[] stages) {
        int[] temp = new int[N + 2];

        List<Node> result = new ArrayList<>();

        Arrays.sort(stages);
        for (int i = 0; i < stages.length; i++) {
            temp[stages[i]] += 1;
        }

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = i; j < temp.length; j++) { sum += temp[j]; }

            if (sum <= 0) result.add(new Node(i, 0));
            else result.add(new Node(i, (float) temp[i] / sum));
        }

        result.sort(Collections.reverseOrder());

        int[] answer = new int[N];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i).getIndex();
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 2, 6, 2, 4, 3, 3};
        solution(5, arr);
    }
}

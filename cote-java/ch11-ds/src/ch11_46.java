import java.util.LinkedList;
import java.util.Queue;

public class ch11_46 {
    static class Solution {
        public static int[] idx = new int[10001];
        public static int solution(int bridge_length, int weight, int[] truck_weights) {
            int t = 0;
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < bridge_length; i++) q.add(0);

            int truckIdx = 0;
            int bridgeWeight = 0;
            while (truckIdx < truck_weights.length) {
                bridgeWeight -= q.poll();
                int truckWeight = truck_weights[truckIdx];
                if (bridgeWeight + truckWeight <= weight) {
                    q.add(truckWeight);
                    bridgeWeight += truckWeight;
                    truckIdx += 1;
                } else {
                    q.add(0);
                }
                t += 1;
            }
            while (bridgeWeight > 0) {
                bridgeWeight -= q.poll();
                t += 1;
            }
            System.out.println(t);
            return t;
        }
    }

    public static void main(String[] args) {
        Solution.solution(100, 100, new int[]{10});
    }
}

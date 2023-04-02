package ch12_58;

import java.util.Stack;

public class ch12_58_1_another {
    static class Solution {

        private record State(int index, int acc) { }

        static int solution(int[] numbers, int target) {
            Stack<State> s = new Stack<>();
            s.push(new State(0, 0));

            int count = 0;
            while (!s.isEmpty()) {
                State state = s.pop();

                if (state.index == numbers.length) {
                    if (state.acc == target) count += 1;
                    continue;
                }
                s.push(new State(state.index + 1, state.acc - numbers[state.index]));
                s.push(new State(state.index + 1, state.acc + numbers[state.index]));
            }

            return count;
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[]{4, 1, 2, 1}, 4);
    }

}

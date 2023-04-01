import java.util.List;

public class ch12_57 {
    static class Solution {
        public static int[] leftFinger = {3, 0};
        public static int[] rightFinger = {3, 2};

        static class NumPad {
            int value;
            int x;
            int y;

            public NumPad(int value,  int y, int x) {
                this.value = value;
                this.y = y;
                this.x = x;
            }

            public NumPad(int value) {
                this.value = value;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                NumPad numPad = (NumPad) o;

                return value == numPad.value;
            }

            @Override
            public int hashCode() {
                return value;
            }
        }

        public static String findFinger(int number, List<NumPad> middle, String hand) {
            NumPad numPad = middle.get(middle.indexOf(new NumPad(number)));
            int left = Math.abs(numPad.y - leftFinger[0]) + Math.abs(numPad.x - leftFinger[1]);
            int right = Math.abs(numPad.y - rightFinger[0]) + Math.abs(numPad.x - rightFinger[1]);

            if (left > right) {
                rightFinger[0] = numPad.y;
                rightFinger[1] = numPad.x;
                return "R";
            } else if (right > left) {
                leftFinger[0] = numPad.y;
                leftFinger[1] = numPad.x;
                return "L";
            } else {
                if (hand.equals("left")) {
                    leftFinger[0] = numPad.y;
                    leftFinger[1] = numPad.x;
                    return "L";
                } else {
                    rightFinger[0] = numPad.y;
                    rightFinger[1] = numPad.x;
                    return "R";
                }
            }
        }

        public static String solution(int[] numbers, String hand) {
            StringBuilder answer = new StringBuilder();
            List<NumPad> left = List.of(
                    new NumPad(1, 0, 0),
                    new NumPad(4, 1, 0),
                    new NumPad(7, 2, 0),
                    new NumPad( '*', 3, 0)
            );

            List<NumPad> right = List.of(
                    new NumPad(3, 0, 2),
                    new NumPad(6, 1, 2),
                    new NumPad(9, 2, 2),
                    new NumPad('#', 3, 2)
            );

            List<NumPad> middle = List.of(
                    new NumPad(2, 0, 1),
                    new NumPad(5, 1, 1),
                    new NumPad(8, 2, 1),
                    new NumPad(0, 3, 1)
            );

            for (int i = 0; i < numbers.length; i++) {
                NumPad now = new NumPad(numbers[i]);
                if (left.contains(now)) {
                    NumPad nowIdx = left.get(left.indexOf(now));
                    leftFinger[0] = nowIdx.y;
                    leftFinger[1] = nowIdx.x;
                    answer.append('L');
                }
                if (right.contains(now)) {
                    NumPad nowIdx = right.get(right.indexOf(now));
                    rightFinger[0] = nowIdx.y;
                    rightFinger[1] = nowIdx.x;
                    answer.append('R');
                }
                if (middle.contains(now)) answer.append(findFinger(numbers[i], middle, hand));
            }

            return answer.toString();
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[]{1, 5}, "right");
    }
}

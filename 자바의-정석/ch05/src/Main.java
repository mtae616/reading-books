import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
//        int[] arr = {10, 20, 30, 40, 50};
//        int sum = Arrays.stream(arr).sum();

        // 5-4
//        int[][] arr = {
//                {5, 5, 5, 5, 5},
//                {10, 10, 10, 10, 10},
//                {20, 20, 20, 20, 20},
//                {30, 30, 30, 30, 30}
//        };
//        AtomicInteger sum = new AtomicInteger();
//        Arrays.stream(arr).forEach(e -> sum.addAndGet(Arrays.stream(e).sum()));
//        float average = (float) sum.get() / (arr.length * arr[0].length);
//        System.out.println(average);

        // 5-5
//        int[] ballArr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        int[] ball3 = new int[3];
//
//        for (int i = 0; i < ballArr.length; i++) {
//            int j = (int) (Math.random() * ballArr.length);
//            int tmp = 0;
//            tmp = ballArr[i];
//            ballArr[i] = ballArr[j];
//            ballArr[j] = tmp;
//        }
//        System.out.println(Arrays.toString(ballArr));
//        System.arraycopy(ballArr, 0, ball3, 0, 3);
//        System.out.println(Arrays.toString(ball3));

        // 5- 6
//        int[] coinUnit = {500, 100, 50, 10};
//        int money = 2680;
//
//        System.out.println("money=" + money);
//
//        for(int i = 0; i < coinUnit.length; i++) {
//            int res = money / coinUnit[i];
//            money %= coinUnit[i];
//            System.out.println(res);
//        }

        // 5-8
//        int[] answer = {1, 4, 4, 3, 1, 4, 4, 2, 1, 3, 2};
//        int[] counter = new int[4];
//
//        Arrays.stream(answer).forEach(e -> counter[e - 1] += 1);
//        Arrays.stream(counter).forEach(e -> System.out.println(e + " " +"*".repeat(e)));

        // 5 - 9
//        char[][] star = {
//                {'*', '*', ' ', ' ', ' '},
//                {'*', '*', ' ', ' ', ' '},
//                {'*', '*', '*', '*', '*'},
//                {'*', '*', '*', '*', '*'}
//        };
//
//        char[][] result = new char[star[0].length][star.length];
//
//        for (int i = 0; i < star.length; i++) {
//            for (int j = 0; j < star[i].length; j++) {
//                System.out.print(star[i][j]);
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//
//        for (int i = 0; i < star.length; i++) {
//            for (int j = 0; j < star[i].length; j++) {
//                result[j][star.length - 1 - i] = star[i][j];
//            }
//        }
//
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(Arrays.toString(result[i]));
//        }

        // 5- 11
        int[][] score = {
                {100, 100, 100},
                {20, 20, 20},
                {30, 30, 30},
                {40, 40, 40},
                {50, 50, 50}
        };

        int[][] result = new int[score.length + 1][score[0].length + 1];

        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[i].length; j++) {
                result[i][j] = score[i][j];
                result[i][score[0].length] += score[i][j];
                result[score.length][j] += result[i][j];
                result[score.length][score[0].length] += result[i][j];
            }
        }

        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }


    }
}
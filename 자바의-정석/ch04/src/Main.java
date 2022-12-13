import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        int i = 0;
        while (i <= 10) {
            System.out.println("*".repeat(i));
            i += 1;
        }

        int value = (int) ((Math.random() * 6) + 1);
        System.out.println(value);

        for (int l = 0; l <= 10; l++) {
            for (int j = 0; j <= 10; j++) {
                if (l != j && 2 * l + 4 * j == 10) {
                    System.out.println("x: " + l + " y: " + j);
                }
            }
        }

        String str = "12345";
        int sum = 0;
        for (int k = 0; k < str.length(); k++) {
            sum += Integer.parseInt(String.valueOf(str.charAt(k)));
        }
        System.out.println(sum);

//        int answer = (int) (Math.random() * 100) + 1;
//        int input = 0;
//        int count = 0;
//
//        Scanner s = new Scanner(System.in);
//
//        do {
//            count++;
//            System.out.println("1과 100 사이의 값을 입력하세요:");
//            input = s.nextInt();
//
//            if (input == answer) {
//                System.out.println("맞췄습니다. 시도 횟수 : " + count);
//                break;
//            } else if (input > answer) {
//                System.out.println("더 작은 값을 입려갛세요");
//            } else {
//                System.out.println("더 큰 값을 입력하세요");
//            }
//        } while (true);

        int number = 12321;
        int tmp = number;
        int result = 0;

        while (tmp != 0) {
            result *= 10;
            result += tmp % 10;
            tmp /= 10;
        }
        System.out.println(result);
    }
}
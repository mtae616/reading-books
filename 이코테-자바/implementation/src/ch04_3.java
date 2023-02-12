import java.util.Scanner;

public class ch04_3 {

    public static void anotherSolution(int row, int column) {
        int dx[] = {2, 1, 2, 1, -2, -1, -2, -1};
        int dy[] = {1, 2, -1, -2, 1, 2, -1, -1};

        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int nx = row + dx[i];
            int ny = row + dy[i];
            if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8) continue;
            cnt += 1;
        }
        System.out.println(cnt);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split("");
        int row = split[0].charAt(0) - 'a';
        int column = split[1].charAt(0) - '0' - 1;
//        anotherSolution(row, column);
        // (2, 1), (1, 2)
        // (2, -1), (1, -2)
        // (-2, 1), (-1, 2)
        // (-2, -1), (-1, -2)
        int[][] movements = {
                {2, 1},
                {1, 2},
                {2, -1},
                {1, -2},
                {-2, 1},
                {-1, 2},
                {-2, -1},
                {-1, -1}
        };

        int cnt = 0;
        for (int i = 0; i < movements.length; i++) {
            int nx = row + movements[i][0];
            int ny = column + movements[i][1];
            if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8) continue;
            cnt += 1;
        }
        System.out.println(cnt);
    }
}

import java.util.Scanner;

public class ch04_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];

        int x = sc.nextInt();
        int y = sc.nextInt();
        int dir = sc.nextInt();

        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String[] lines = sc.nextLine().split(" ");
            for (int j = 0 ; j < lines.length; j++) {
                arr[i][j] = lines[j].charAt(0) - '0';
            }
        }
        int sum = 1;
        int dirCnt = 0;

        while (true) {
            if (dirCnt >= 4) break;
            for (int i = 3; i >= 0; i--) {
                int nx = x + dx[i - dir];
                int ny = y + dy[i - dir];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (arr[ny][nx] == 0) {
                    x = nx;
                    y = ny;
                    System.out.println();
                    dirCnt = 0;
                    arr[ny][nx] = 1;
                    sum += 1;
                }
                else dirCnt += 1;
            }
        }

        System.out.println(sum);


    }
}

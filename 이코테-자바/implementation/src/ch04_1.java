import java.util.*;

public class ch04_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        String[] movement = sc.nextLine().split(" ");

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Map<String, Integer> idx = new HashMap<>();
        idx.put("R", 0);
        idx.put("D", 1);
        idx.put("L", 2);
        idx.put("U", 3);


        int resX = 0;
        int resY = 0;
        for (String m : movement) {
            int nx = resX + dx[idx.get(m)];
            int ny = resY + dy[idx.get(m)];
            if (nx < 0 || nx >= n || ny < 0 || nx >= n) continue ;
            resX = nx;
            resY = ny;
        }
        System.out.println((resY + 1) + " " + (resX + 1));
    }
}
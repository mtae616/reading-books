import java.io.*;
import java.util.ArrayList;

public class ch16_32 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) arr.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < s.length; j++) {
                arr.get(i).add(Integer.parseInt(s[j]));
            }
        }

        for (int i = 1; i < n; i++) {
            ArrayList<Integer> now = arr.get(i);
            for (int j = 0; j < now.size(); j++) {
                if (i == j) {
                    now.set(j, now.get(j) + arr.get(i - 1).get(j - 1));
                    continue;
                }
                int up = 0;
                if (j - 1 >= 0) up = now.get(j) + arr.get(i - 1).get(j - 1);
                int rightUp = now.get(j) + arr.get(i - 1).get(j);
                now.set(j, Math.max(up, rightUp));
            }
        }

        int max = 0;
        ArrayList<Integer> end = arr.get(n - 1);
        for (int i = 0; i < end.size(); i++) {
            if (max < end.get(i)) max = end.get(i);
        }

        bw.write(String.valueOf(max));

        bw.flush();
        br.close();
        bw.close();
    }
}

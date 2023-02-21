import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ch12_07 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        int mid = s.length() / 2;
        String start = s.substring(0, mid);
        String end = s.substring(mid);
        int sum = Arrays.stream(start.split("")).mapToInt(Integer::parseInt).sum();
        int sum2 = Arrays.stream(end.split("")).mapToInt(Integer::parseInt).sum();
        if (sum == sum2) System.out.println("LUCKY");
        else System.out.println("READY");
        bf.close();
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ch07_fasterThanScanner {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        System.out.println(s);

        int a = Integer.parseInt(bf.readLine());
        int b = Integer.parseInt(bf.readLine());

        System.out.println(a);
        System.out.println(b);

        String[] s1 = bf.readLine().split(" ");
        List<Integer> arrInt = new ArrayList<>();
        for (int i = 0; i < s1.length; i++) {
            arrInt.add(Integer.parseInt(s1[i]));
        }

        for (int i = 0; i < arrInt.size(); i++) {
            System.out.println(arrInt.get(i));
        }

        bf.close();
    }
}

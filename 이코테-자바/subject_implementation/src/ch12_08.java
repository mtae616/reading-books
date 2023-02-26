import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ch12_08 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Character> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        String[] arr = br.readLine().split("");

        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i].charAt(0))) {
                arr2.add(Integer.parseInt(arr[i]));
            } else {
                arr1.add(arr[i].charAt(0));
            }
        }

        Collections.sort(arr1);
        Collections.sort(arr2);

        for (int i = 0; i < arr1.size(); i++) { bw.write(arr1.get(i)); }
        for (int i = 0; i < arr2.size(); i++) { bw.write(String.valueOf(arr2.get(i))); }

        bw.flush();
        br.close();
        bw.close();
    }
}

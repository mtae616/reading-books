import java.io.*;

public class ch11_02 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = bf.readLine();
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int now = Integer.parseInt(String.valueOf(s.charAt(i)));
            if (now <= 1 || sum <= 1) sum += now;
            else sum *= now;
        }

        bw.write(String.valueOf(sum));
        bw.write(" ");
        bw.flush();
        bf.close();
    }
}

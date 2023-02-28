import java.io.*;

public class ch11_03 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();

        int zCnt = 0;
        int oCnt = 0;

        int i = 0;
        while (i < s.length()) {
            char buf = s.charAt(i);

            if (buf == '0') zCnt += 1;
            else oCnt += 1;

            while (i < s.length() && buf == s.charAt(i)) { i += 1; }
        }

        bw.write(String.valueOf(Math.min(zCnt, oCnt)));

        bw.flush();
        br.close();
        bw.close();
    }
}

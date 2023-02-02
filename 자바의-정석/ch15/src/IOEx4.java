import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class IOEx4 {
    public static void main(String[] args) {
        byte[] inSrc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        byte[] outSrc = null;
        byte[] temp = new byte[4];

        ByteArrayInputStream input = null;
        ByteArrayOutputStream output = null;

        input = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

        try {
            while (input.available() > 0) {
                int len = input.read(temp); // 읽어 온 데이터의 개수를 반환
                output.write(temp, 0, len); // 일어 온 만큼만 write
            }
        } catch (IOException e) {}

        outSrc = output.toByteArray();

        System.out.println(Arrays.toString(outSrc));
    }
}

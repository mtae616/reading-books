import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class SerialEx2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "UserInfo.ser";
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream in = new ObjectInputStream(bis);

        SerialEx1.UserInfo u1 = (SerialEx1.UserInfo) in.readObject();
        SerialEx1.UserInfo u2 = (SerialEx1.UserInfo) in.readObject();
        ArrayList list = (ArrayList) in.readObject();

        System.out.println(u1);
        System.out.println(u2);
        System.out.println(list);

        in.close();
    }
}

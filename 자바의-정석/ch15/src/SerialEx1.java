import java.io.*;
import java.util.ArrayList;

public class SerialEx1 {
    public static void main(String[] args) throws IOException {
        String fileName = "UserInfo.ser";
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        ObjectOutputStream out = new ObjectOutputStream(bos);

        UserInfo u1 = new UserInfo("javaman", "1234", 30);
        UserInfo u2 = new UserInfo("javawoman", "4321", 26);

        ArrayList<UserInfo> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);

        out.writeObject(u1);
        out.writeObject(u2);
        out.writeObject(list);
        out.close();
    }

    static class UserInfo implements Serializable {
        String name;
        String password;
        int age;

        public UserInfo() {
            this("Unknown", "1111", 0);
        }

        public UserInfo(String name, String password, int age) {
            this.name = name;
            this.password = password;
            this.age = age;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

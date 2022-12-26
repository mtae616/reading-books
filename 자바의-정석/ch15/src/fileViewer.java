import java.io.*;

public class fileViewer {
    public static void main(String[] args) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(args[0]);
//        int data = 0;
//        while ((data = fileInputStream.read()) != -1) {
//            char c = (char) data;
//            System.out.print(c);
//        }

//        FileInputStream fis = new FileInputStream(args[0]);
//        FileOutputStream fos = new FileOutputStream(args[1]);
//
//        int data = 0;
//
//        while ((data = fis.read()) != -1) {
//            fos.write(data);
//        }
//        fis.close();
//        fos.close();

        PrintWriter pw = new PrintWriter(System.out);
        pw.println("test");
        pw.flush();
        pw.println("test2");
        pw.flush();
        System.out.println("hi!");
    }
}

package ClassVariableAndMethod;

import java.time.LocalDate;
import static java.lang.Math.*;

public class AnotherUsingOfStatic {
    static String date;
    static { // static 초기화 블럭
        LocalDate nDate = LocalDate.now();
        date = nDate.toString();
    }
    public static void main(String[] args) {
        System.out.println(date);
        System.out.println(PI);
    }
}

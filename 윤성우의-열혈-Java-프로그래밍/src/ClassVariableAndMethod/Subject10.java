package ClassVariableAndMethod;

class Accmulator {
    static int num;
    static void add(int i) {
        num += i;
    }
    static void showResult() {
        System.out.println(num);
    }
}

public class Subject10 {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            Accmulator.add(i);
        }
        Accmulator.showResult();
    }
}

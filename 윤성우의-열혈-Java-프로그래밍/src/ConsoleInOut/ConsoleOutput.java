package ConsoleInOut;

public class ConsoleOutput {
    public static void main(String[] args) {
        // sout은 참조 값이 전달되면, 이 값의 인스턴스를 대상으로 toString 메소드를 호출하여 이때 반환되는 문자열을 출력한다.
        StringBuilder buil = new StringBuilder("asd");
        System.out.println(buil);

        System.out.printf("%d", 12); // printf
    }
}

class BankAccount {
    int balance = 0;
    public int deposit(int amount) {
        balance += amount;
        return balance;
    }
    public int withdraw(int amount) {
        balance -= amount;
        return balance;
    }
    public int checkMyBalance() {
        System.out.println("현재 잔액 : " + balance);
        return balance;
    }
}

public class classandinstance {
    // 클래스 = 데이터 + 메소드
    // 데이터 : 프로그램상에서 유지하고 관리해야 할 데이터
    // 기능 : 데이터를 처리하고 조작하는 기능
    // 인스턴스 변수 : 클래스 내에 선언된 변수
    // 인스턴스 메소드 : 클래스 내에 정의된 메소드


    public static void main(String[] args) {
        BankAccount b = new BankAccount();
        // new 를 통해 인스턴스를 생성하면 생선된 인스턴스의 주솟값이 반횐된다.
        // 즉 참조변수에는 생성된 인스턴스의 주솟값이 저장되는 셈이다.
        b.deposit(1000);
        b.withdraw(500);
        check(b); // 파라미터로 인스턴스의 참조 값을 전달 할 수 있다.
    }

    public static void check(BankAccount acc) {
        acc.checkMyBalance();
    }
}

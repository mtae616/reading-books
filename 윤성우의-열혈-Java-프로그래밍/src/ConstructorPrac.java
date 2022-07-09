class BankAccount2 {
    String accNumber;
    String ssNumber;
    int balance;

    // 객체의 초기화를 위해 생성자 사용
    // 생성자의 이름은 클래스의 이름과 동일해야 한다.
    // 생성자는 값을 반환하지 않고 반환형도 표시하지 않는다.
    // 인스턴스 생성의 마지막 단계는 생성자 호출이다.
    // 어떠한 이유로든 생성자 호출이 생략된 인스턴스는 인스턴스가 아니다.
    public BankAccount2(String accNumber, String ssNumber, int balance) {
        this.accNumber = accNumber;
        this.ssNumber = ssNumber;
        this.balance = balance;
    }

    public int deposit(int amount) {
        balance += amount;
        return balance;
    }

    public int withdraw(int amount) {
        balance -= amount;
        return balance;
    }

    public int checkMyBalance() {
        System.out.println("계좌번호 : " + accNumber);
        System.out.println("주민번호 : " + ssNumber);
        System.out.println("잔액 : " + balance);
        return balance;
    }
}

public class ConstructorPrac {
    public static void main(String[] args) {
        BankAccount2 t = new BankAccount2("12-34-56", "909090-909090", 10000);
        t.deposit(3000);
        t.checkMyBalance();
    }
}

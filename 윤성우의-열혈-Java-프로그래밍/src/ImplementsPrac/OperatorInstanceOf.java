package ImplementsPrac;

public class OperatorInstanceOf {
    public static void main(String[] args) {
        Cake cake = new StrawberryCheeseCake(3, 4);
        if (cake instanceof Cake) // true
            System.out.println("케익 인스턴스");
        if (cake instanceof CheeseCake) // true
            System.out.println("치즈 케익 인스턴스");
        if (cake instanceof StrawberryCheeseCake) // true
            System.out.println("딸기 치즈 케익");
    }
}

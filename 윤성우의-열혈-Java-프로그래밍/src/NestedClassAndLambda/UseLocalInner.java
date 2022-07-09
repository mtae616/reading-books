package NestedClassAndLambda;

interface Eatable {
    void print();
}

class Fruit {
    private String con;

    public Fruit(String con) {
        this.con = con;
    }

    public Eatable getEatable() { // 메소드 내에서만 생성
        class Mango implements Eatable {
            public void print() {
                System.out.println(con);
            }
        }
        return new Mango();
    }
}

public class UseLocalInner {
    public static void main(String[] args) {
        Fruit f = new Fruit("망고 조아!");
        Eatable ea = f.getEatable();
        ea.print();
    }
}

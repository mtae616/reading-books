package NestedClassAndLambda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface Printable {
    void print();
    void print(String s);
}

class Papers {
    private String con;

    public Papers(String con) {
        this.con = con;
    }

    public Printable getPrinter() {
        return new Printer(); // 멤버 클래스 인스턴스 생성 및 반환
    }

    private class Printer implements Printable { // 멤버 클래스의 정의
        public void print() {
            System.out.println(con);
        }

        @Override
        public void print(String s) {

        }
    }
}

public class UseMemeberInner {
    public static void main(String[] args) {
        Papers p = new Papers("서류 내용: 행복합니다.");
        // iterator와 비슷한 느낌
        // List<String> list = new ArrayList<>();
        // Iterator<String> itr = list.iterator();
        Printable prn = p.getPrinter();
        prn.print();
    }
}

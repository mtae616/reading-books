package MemoryModelAndObjectClass;

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        // 소멸되기 전에 자동으로 호출되는 메소드, 호출이 생략되는 경우도 있음...
        // 그럼 이거 왜 쓰지...
        super.finalize(); // 상위 클래스(Object)의 finalize 메소드 호출
        System.out.println("destroyed: " + name);
    }
}


public class ObjectFinallize {
    public static void main(String[] args) {
        Person p1 = new Person("Yoon");
        Person p2 = new Person("Park");
        p1 = null; // 참조대상을 가비지 컬렉션의 대상으로 만듦
        p2 = null; // 참조대상을 가비지 컬렉션의 대상으로 만듦
    }
}

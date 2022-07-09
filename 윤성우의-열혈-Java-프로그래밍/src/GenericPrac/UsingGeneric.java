package GenericPrac;

class Apple {
    public String toString() {
        return "I am an apple";
    }
}

class Orange {
    public String toString() {
        return "I am an orange";
    }
}

class Box<T> {
    private T ob;

    public T getOb() {
        return ob;
    }

    public void setOb(T ob) {
        this.ob = ob;
    }
}

public class UsingGeneric {
    public static void main(String[] args) {
        // Box<T> T는 타입 매개변수
        // Box<Apple> Apple은 타입 이낮
        // Box<Apple> 매개변수화 타입
        Box<Apple> aBox = new Box<Apple>();
        Box<Orange> bBox = new Box<Orange>();

        aBox.setOb(new Apple());
        bBox.setOb(new Orange());

        Apple ap = aBox.getOb();
        Orange og = bBox.getOb();

        System.out.println(ap);
        System.out.println(og);
    }
}

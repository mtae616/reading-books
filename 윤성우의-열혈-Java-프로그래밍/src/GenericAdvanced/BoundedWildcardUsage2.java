package GenericAdvanced;

class Toy {
    @Override
    public String toString() { return "I am a Toy"; }
}

class BoxHandler {
    public static void outBox(Box<? extends Toy> box) {
        // 박스가 참조하는 인스턴스를 대상으로 꺼내는 작업만 허용하겠다는 의미
        // 아래에서 box.set은 컴파일 에러
        // 매개변수로 toy 인스턴스를 저장할 수 있는 상자만 전달된다는 사실을 보장할 수 없기 때문에
        // ? 가 toy보다 작은애라 set안됨
        Toy t = box.getOb();
        System.out.println(t);
    }
    public static void inbox(Box<? super Toy> box, Toy n) {
        // 박스가 참조하는 인스턴스 대상으로 넣는 작업만 허용하겠다는 의미
        // get 메소드는 반환되는 값을 저장하기 위해 선언한 참조변수의 형을 Toy로 결정했다는 사실에서 문제가 발생
        // super 면 반환 값이 toy 이거나 Toy 이상이라 get 안됨
        box.setOb(n);
    }
}

public class BoundedWildcardUsage2 {
    public static void main(String[] args) {
        Box<Toy> box = new Box<>();
        BoxHandler.inbox(box, new Toy());
        BoxHandler.outBox(box);
    }
}

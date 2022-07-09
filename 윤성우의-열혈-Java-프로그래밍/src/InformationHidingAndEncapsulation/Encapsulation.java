package InformationHidingAndEncapsulation;

class SinivelCap {
    void take() {
        System.out.println("콧물이 싹~ 납니다.");
    }
}

class SneezeCap {
    void take() {
        System.out.println("재채기가 멎습니다.");
    }
}

class SnuffleCap {
    void take() {
        System.out.println("코가 뻥 뚫립니다.");
    }
}

class SinusCap {
    void sniTake() {
        System.out.println("콧물이 싹~ 납니다.");
    }
    void sneTake() {
        System.out.println("재채기가 멎습니다.");
    }
    void snuTake() {
        System.out.println("코가 뻥 뚫립니다.");
    }

    void take() {
        sniTake();
        sneTake();
        snuTake();
    }

    void take2() {
        SinivelCap sicap = new SinivelCap();
        SneezeCap sncap = new SneezeCap();
        SnuffleCap sfcpa = new SnuffleCap();
        sicap.take(); sncap.take(); sfcpa.take();
    }
}

class ColdPatient {
    void takeSinus(SinusCap cap) {
        cap.take();
    }

    void takeSinus2(SinusCap cap) {
        cap.take2();
    }
}

public class Encapsulation {
    // 하나의 목적을 이루기 위해 관련 있는 모든 것을 하나의 클래스에 담아 두는 것
    public static void main(String[] args) {
        ColdPatient suf = new ColdPatient();
        suf.takeSinus(new SinusCap());
        suf.takeSinus2(new SinusCap());
    }
}

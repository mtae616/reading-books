package ImplementsPrac;

class car {
    int gasolineGauge;

    public car(int gasolineGauge) {
        this.gasolineGauge = gasolineGauge;
    }
}

class HybridCar extends car {
    int electricGuage;

    public HybridCar(int gasolineGauge, int electricGuage) {
        super(gasolineGauge);
        this.electricGuage = electricGuage;
    }
}

class HybridWaterCar extends HybridCar {
    int waterGauge;

    public HybridWaterCar(int gasolineGauge, int electricGuage, int waterGauge) {
        super(gasolineGauge, electricGuage);
        this.waterGauge = waterGauge;
    }

    public void showCurrenGauge() {
        System.out.println("잔여 가솔린 : " + this.gasolineGauge);
        System.out.println("잔여 전기량 : " + this.electricGuage);
        System.out.println("잔여 워터량 : " + this.waterGauge);
    }
}

public class Subject14 {
    public static void main(String[] args) {
        HybridWaterCar hc = new HybridWaterCar(1, 2, 3);
        hc.showCurrenGauge();
    }
}

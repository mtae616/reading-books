package Streams;

import java.util.ArrayList;
import java.util.List;

class ToyPriceInfo {
    private String model;
    private int price;

    public ToyPriceInfo(String model, int price) {
        this.model = model;
        this.price = price;
    }
    public int getPrice() {
        return this.price;
    }

    public String getModel() {
        return model;
    }
}

public class ToyStream {
    public static void main(String[] args) {
        List<ToyPriceInfo> ls = new ArrayList<>();
        ls.add(new ToyPriceInfo("GUN_LR_45", 200));
        ls.add(new ToyPriceInfo("TEDDY_BEAR_S_014", 350));
        ls.add(new ToyPriceInfo("CAR_TRANSFORM_VER_7719", 550));
        int sum = ls.stream().filter(e -> e.getPrice() < 500).mapToInt(e -> e.getPrice()).sum();
        ls.stream().filter(e -> e.getModel().length() > 10).forEach(e -> System.out.println(e.getModel()));
        System.out.println(sum);
    }
}

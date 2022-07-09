package CollectionFrameworkPrac2;

import java.util.*;

class Car implements Comparable<Car> {
    protected int disp;

    public Car(int disp) {
        this.disp = disp;
    }

    @Override
    public String toString() {
        return "cc: " + disp;
    }
    @Override
    public int compareTo(Car o) {
        return disp - o.disp;
    }
}

class CarComp implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) { return o1.disp - o2.disp; }
}

class ECar extends Car {
    private int battery;

    public ECar(int disp, int batter) {
        super(disp);
        this.battery = batter;
    }

    @Override
    public String toString() {
        return "cc : " + disp + ", ba : " + battery;
    }
}

public class CarSortCollections {
    public static void main(String[] args) {
        List<Car> list = new ArrayList<>();
        list.add(new Car(1200));
        list.add(new Car(3000));
        list.add(new Car(1800));


        System.out.println();
        List<ECar> list2 = new ArrayList<>();
        list2.add(new ECar(1200, 99));
        list2.add(new ECar(3000, 85));
        list2.add(new ECar(1800, 80));
        Collections.sort(list, new CarComp());
        Collections.sort(list2, new CarComp());
        // 얘도 가능
        // 매개변수 C를 대상으로는 T형 인스턴스를 넣는 메소드 호출만 OK

        for(Car c : list)
            System.out.println(c);

        System.out.println();

        for(Iterator<ECar> itr = list2.iterator(); itr.hasNext();)
            System.out.println(itr.next());
    }
}

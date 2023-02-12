import java.util.*;

class Fruit implements Comparable<Fruit> {
    private String name;
    private int score;

    @Override
    public int compareTo(Fruit o) {
        if (this.score < o.score) {
            return -1;
        }
        return 1;
    }

    public Fruit(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}

public class ch06_SortLib {
    public static void main(String[] args) {
        int[] array = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        int[] ints = Arrays.stream(array).sorted().toArray();
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }

        System.out.println();

        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("바나나", 2));
        fruits.add(new Fruit("사과", 5));
        fruits.add(new Fruit("당근", 3));

        Collections.sort(fruits);

        for (int i = 0; i < fruits.size(); i++) {
            System.out.println(fruits.get(i));
        }
    }
}

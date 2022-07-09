package Arrays;

class Box2 {
    private String contents;
    private int boxNum;

    public Box2(String contents, int boxNum) {
        this.contents = contents;
        this.boxNum = boxNum;
    }
    public int getBoxNum() {
        return this.boxNum;
    }
    public String toString() {
        return this.contents;
    }

}

public class EnhancedFor {
    public static void main(String[] args) {
        int[] ar = {1, 2, 3, 4, 5};
        for(int e : ar) {
            System.out.println(e);
        }
        Box2[] boxAr = new Box2[5];
        boxAr[0] = new Box2("Coffe", 101);
        boxAr[1] = new Box2("Computer", 202);
        boxAr[2] = new Box2("Apple", 303);
        boxAr[3] = new Box2("Dress", 404);
        boxAr[4] = new Box2("Fairy-tale boox", 505);
        for(Box2 e : boxAr) {
            if (e.getBoxNum() == 505)
                System.out.println(e);
        }
    }
}

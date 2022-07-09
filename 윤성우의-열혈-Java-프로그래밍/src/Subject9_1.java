class Point {
    int xPos, yPos;

    public Point(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    public void showPointInfo() {
        System.out.println("[" + xPos + ", " + yPos + "]");
    }
}

class Circle {
    int x, y, rad;

    public Circle(int x, int y, int rad) {
        this.x = x;
        this.y = y;
        this.rad = rad;
    }
    public void showCircleInfo() {
        System.out.println("[" + x + ", " + y + "]" + " rad : " + rad);
    }
}

public class Subject9_1 {
    public static void main(String[] args) {
        Circle c = new Circle(2, 2, 4);
        c.showCircleInfo();
    }
}

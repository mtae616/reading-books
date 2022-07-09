package InformationHidingAndEncapsulation;

class Circle {
    private double rad = 0; // 반지름
    final double PI = 3.14;

    public Circle(double rad) {
        setRad(rad);
    }
    public double getRad() {
        return this.rad;
    }

    public void setRad(double r) {
        if (r < 0) {
            rad = 0;
            return;
        }
        rad = r;
    }

    public double getArea() {
        return (this.rad * this.rad) * PI;
    }
}

public class InformationHiding {
    public static void main(String[] args) {
        Circle c = new Circle(1.5);
        System.out.println(c.getArea());

        c.setRad(2.5);
        System.out.println(c.getArea());

        c.setRad(-3.3);
        System.out.println(c.getArea());


        // c.rad = -4.5; // 문제가 되는 부분 private 키워드를 통해 외부에서 접근하지 못하게 한다

    }
}

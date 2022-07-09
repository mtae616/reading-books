package PrimitiveClass;

public class BoxingAndUnBoxing {
    public static void main(String[] args) {
        Integer iObj = new Integer(10);
        Double dObj = new Double(3.14);
        System.out.println(iObj);
        System.out.println(dObj);
        System.out.println();

        int num1 = iObj.intValue();
        double num2 = dObj.doubleValue();
        System.out.println(num1);
        System.out.println(num2);
        System.out.println();

        // 인스턴스 값 수정 불가, 값 새로 할당 할 때는 인스턴스 새로 할당
        iObj = new Integer(iObj.intValue() + 10);
        dObj = new Double(dObj.doubleValue() + 1.2);
        System.out.println(iObj);
        System.out.println(dObj);
    }
}

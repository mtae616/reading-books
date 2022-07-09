package Arrays;

import java.util.Arrays;

class Box {
    private String conts;

    public Box(String conts) {
        this.conts = conts;
    }
    public String toString() {
        return conts;
    }
}

public class OnedArray {
    public static void main(String[] args) {
        // 타입이 같은 둘 이상의 데이터를 저장할 수 있는 1차원 구조의 메모리 공간
        // 자바에서는 배열도 인스턴스이다.
        int[] ref; // 참조변수의 선언
        ref = new int[5]; // length = 5
        System.out.println(ref.length);

        Box[] ar = new Box[5]; // box instance 5개 저장
        System.out.println(ar.length); // length 5

        ar[0] = new Box("First");
        ar[1] = new Box("Second");
        ar[2] = new Box("Third");

        System.out.println(ar[0]); // toString 이라는 변수가 있어서 String을 호출
        System.out.println(ar[1]);
        System.out.println(ar[2]);

        int[] intAr = new int[10]; // 배열의 모든 요소 0으로 초기화
        int[] intAr2 = new int[10];
        String[] stringAr = new String[10]; // 배열의 모든 요소 null로 초기화

        Arrays.fill(intAr, 1); // 1로 채워줌
        System.arraycopy(intAr, 0, intAr2, 0, 10); // arraycopy
        for(int i = 0; i < intAr.length; i++)
            System.out.println(intAr[i]);
        System.out.println();
        for(int i = 0; i < intAr2.length; i++)
            System.out.println(intAr2[i]);
    }
}

import java.util.Arrays;

public class Ch06_CountSort {
    public static void main(String[] args) {
        int[] array = {7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2};
        int max = Arrays.stream(array).max().getAsInt();
        int[] count = new int[max + 1];


        for (int i = 0; i < array.length; i++) count[array[i]] += 1;

        for (int i = 0; i < count.length; i++) {
            if(count[i] != 0) System.out.print(i + " ");
        }


    }
}

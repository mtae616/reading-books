public class ch06_SelectionSort {
    public static void main(String[] args) {
        int[] array = {7, 5, 9, 0, 1, 6, 2, 4, 8};

        for (int i = 0; i < array.length; i++) {
            int minIdx = i;

            for (int j = i + 1; j < array.length; j++) if (array[minIdx] > array[j]) minIdx = j;

            int temp = array[i];
            array[i] = array[minIdx];
            array[minIdx] = temp;
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
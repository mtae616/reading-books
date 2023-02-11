public class ch06_InsertionSort {
    public static void main(String[] args) {
        int[] array = {7, 5, 9, 0, 1, 6, 2, 4, 8};

        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j -= 1) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                } else {
                    break;
                }
            }
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

    }
}

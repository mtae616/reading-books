package Arrays;

public class TwoDArray {
    public static void addOneDArr(int[] arr, int add) {
        for (int i = 0; i < arr.length; i++)
            arr[i] += add;
    }
    public static void addTwoDArr(int[][] arr, int add) {
        for(int i = 0; i < arr.length; i++) {
            addOneDArr(arr[i], add);
        }
    }

    public static void parser(int[][] arr) {
        int temp[];
        temp = arr[0];
        arr[0] = arr[2];
        arr[2] = arr[1];
        arr[1] = temp;
        for(int i = 0; i <arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
//        int arr[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
//        addTwoDArr(arr, 1);
//        for(int i = 0; i < arr.length; i++) {
//            for(int j = 0; j < arr[i].length; j++)
//                System.out.println(arr[i][j]);
//        }

        int arr2[][] = {{1,2,3}, {4,5,6}, {7,8,9}};
        parser(arr2);
    }
}

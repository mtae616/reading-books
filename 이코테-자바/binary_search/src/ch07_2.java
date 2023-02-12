import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class ch07_2 {

    public static void anotherSolution(int[] arr, int[] arr1) {
        HashSet<Integer> convertedArr = new HashSet<>();
        Arrays.stream(arr).forEach(convertedArr::add);

        for (int i = 0; i < arr1.length; i++) {
            if (convertedArr.contains(arr1[i])) System.out.println("yes");
            else System.out.println("no");
        }
    }

    public static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] > target) end = mid - 1;
            else start = mid + 1;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        String[] s = bf.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < s.length; i++) arr[i] = Integer.parseInt(s[i]);
        Arrays.sort(arr);

        int m = Integer.parseInt(bf.readLine());
        String[] s1 = bf.readLine().split(" ");
        int[] arr1 = new int[m];
        for (int i = 0; i < s1.length; i++) arr1[i] = Integer.parseInt(s1[i]);

        anotherSolution(arr, arr1);
//        for (int i = 0; i < arr1.length; i++) {
//            int result = binarySearch(arr, arr1[i]);
//            if (result == -1) System.out.println("no");
//            else System.out.println("yes");
//        }

        bf.close();
    }
}

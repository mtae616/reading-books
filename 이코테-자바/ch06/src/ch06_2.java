import java.util.*;

public class ch06_2 {

    public static void anotherSolution() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr, Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        ArrayList<Integer> arr = new ArrayList<>();
//        int n = sc.nextInt();
//        sc.nextLine();
//        for (int i = 0; i < n; i++) {
//            arr.add(sc.nextInt());
//            sc.nextLine();
//        }
//
//        arr.sort(Comparator.reverseOrder());
//        for (int i = 0; i < arr.size(); i++) {
//            System.out.print(arr.get(i) + " ");
//        }

        anotherSolution();
    }
}

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 1
//        ArrayList list1 = new ArrayList();
//        ArrayList list2 = new ArrayList();
//
//        ArrayList kyo = new ArrayList();
//        ArrayList cha = new ArrayList();
//        ArrayList hap = new ArrayList();
//
//        list1.add(1);
//        list1.add(2);
//        list1.add(3);
//        list1.add(4);
//
//        list2.add(3);
//        list2.add(4);
//        list2.add(5);
//        list2.add(6);
//
//        for (Object o : list1) {
//            if (list2.contains(o)) {
//                kyo.add(o);
//            } else {
//                cha.add(o);
//            }
//            hap.add(o);
//        }
//
//        list2.forEach(e -> {
//            if (!hap.contains(e)) {
//                hap.add(e);
//            }
//        });
//
//        System.out.println("list1=" + list1);
//        System.out.println("list2=" + list2);
//
//        System.out.println("kyo=" + kyo);
//        System.out.println("cha=" + cha);
//        System.out.println("hap=" + hap);

        // 10
        Set set = new HashSet();
        int[][] board = new int[5][5];
        for(int i=0; set.size() < 25; i++) {
            set.add((int)(Math.random()*30)+1+"");
        }

        ArrayList list = new ArrayList(set);
        Collections.shuffle(list);
        Iterator it = list.iterator();
        for(int i=0; i < board.length; i++) {
            for(int j=0; j < board[i].length; j++) {
                board[i][j] = Integer.parseInt((String)it.next());
                System.out.print((board[i][j] < 10 ? " " : " ")
                        + board[i][j]);
            }
            System.out.println();
        }

    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ch11_49 {

    static class Solution {
        static class Node implements Comparable<Node>{
            int x;
            int y;
            int value;
            Node left;
            Node right;

            public Node(int x, int y, int value) {
                this.x = x;
                this.y = y;
                this.value = value;
            }

            @Override
            public int compareTo(Node o) {
                if (this.y > o.y) return -1;
                else if (this.y == o.y) return this.x - o.x;
                return 1;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "value=" + value +
                        ", y=" + y +
                        ", x=" + x +
                        '}';
            }
        }

        private static void insert(Node root, Node node) {
            if (node.x < root.x) {
                if (root.left == null) root.left = node;
                else insert(root.left, node);
            } else {
                if (root.right == null) root.right = node;
                else insert(root.right, node);
            }
        }

        private static Node contruct(List<Node> nodeList) {
            Node root = nodeList.get(0);
            for (int i = 1; i < nodeList.size(); i++) {
                insert(root, nodeList.get(i));
            }
            return root;
        }

        private static void pre(Node node, List<Integer> visits) {
            if (node == null) return;
            visits.add(node.value);
            pre(node.left, visits);
            pre(node.right, visits);
        }

        private static void post(Node node, List<Integer> visits) {
            if (node == null) return;
            post(node.left, visits);
            post(node.right, visits);
            visits.add(node.value);
        }

        public static int[][] solution(int[][] nodeinfo) {
            int[][] answer = {};

            List<Node> nodeList = new ArrayList<>();

            for (int i = 0; i < nodeinfo.length; i++) {
                int[] arr = nodeinfo[i];
                int x = arr[0];
                int y = arr[1];
                nodeList.add(new Node(x, y, i + 1));
            }

            Collections.sort(nodeList);
            Node root = contruct(nodeList);

            List<Integer> preVisit = new ArrayList<>();
            pre(root, preVisit);

            List<Integer> postVisit = new ArrayList<>();
            post(root, postVisit);
            return new int[][] {
                    preVisit.stream().mapToInt(Integer::intValue).toArray(),
                    postVisit.stream().mapToInt(Integer::intValue).toArray()
            };
        }
    }

    public static void main(String[] args) {
        Solution.solution(new int[][]{
                {5, 3},
                {11, 5},
                {13, 3},
                {3, 5},
                {6, 1},
                {1, 3},
                {8, 6},
                {7, 2},
                {2, 2}
        });
    }
}

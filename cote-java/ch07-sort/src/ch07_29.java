import java.util.*;
import java.util.stream.Collectors;

public class ch07_29 {
    static class Solution {
        public static int[] visited;
        static class Node implements Comparable<Node> {
            String name;
            int value;

            public Node(String s, int value) {
                this.name = s;
                this.value = value;
            }

            public Node(String name) {
                this.name = name;
            }

            @Override
            public int compareTo(Node o) {
                return this.name.compareTo(o.name);
            }

            @Override
            public String toString() {
                return "Node{" +
                        "s='" + name + '\'' +
                        ", value=" + value +
                        '}';
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Node node = (Node) o;

                return Objects.equals(name, node.name);
            }

            @Override
            public int hashCode() {
                return name != null ? name.hashCode() : 0;
            }
        }


        public static void dfs(List<List<Node>> nodes, String[] orders, List<Integer> courseList, String origin, String now) {
            if (courseList.contains(now.length())) {
                int wholeSum = 0;
                for (int i = 0; i < orders.length; i++) {
                    int sum = 0;
                    for (int j = 0; j < now.length(); j++) {
                        if (orders[i].indexOf(now.charAt(j)) != -1) sum += 1;
                    }
                    if (sum == now.length()) wholeSum += 1;
                }
                char[] chars = now.toCharArray();
                Arrays.sort(chars);

                String converted = String.valueOf(chars);

                if (!nodes.get(converted.length()).contains(new Node(converted))
                    && wholeSum > 1) {
                    nodes.get(converted.length()).add(new Node(converted, wholeSum));
                }

            }

            for (int i = 0; i < origin.length(); i++) {
                if (visited[i] == 0) {
                    visited[i] = 1;
                    dfs(nodes, orders, courseList, origin, now + origin.charAt(i));
                    visited[i] = 0;
                }
            }

        }
        public static String[] solution(String[] orders, int[] course) {
            List<Integer> courseList = Arrays.stream(course).boxed().collect(Collectors.toList());

            List<List<Node>> nodes = new ArrayList<>();
            for (int i = 0; i <= 10; i++) nodes.add(new ArrayList<>());

            for (int i = 0; i < orders.length; i++) {
                visited = new int[orders[i].length()];
                dfs(nodes, orders, courseList, orders[i], "");
            }

            for(int i = 0; i <= 10; i++) Collections.sort(nodes.get(i), (a, b) -> b.value - a.value);
            List<String> answers = new ArrayList<>();
            for (int i = 0; i < nodes.size(); i++) {
                List<Node> now = nodes.get(i);
                if (now.size() > 0) {
                    int max = now.get(0).value;
                    for (int j = 0; j < now.size(); j++) {
                        if (now.get(j).value == max) {
                            answers.add(now.get(j).name);
                        }
                    }
                }
            }

            Collections.sort(answers, String::compareTo);

            return answers.toArray(new String[0]);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution.solution(
                new String[]{"XYZ", "XWY", "WXA"},
                new int[]{2, 3, 4})));
    }
}

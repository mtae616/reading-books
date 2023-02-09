import java.util.ArrayList;

class Node_temp {
    private int index;
    private int distance;

    public Node_temp(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public void show() {
        System.out.print("(" + this.index + "," + this.distance + ") ");
    }
}

public class ch05_adjust_graph {

    public static ArrayList<ArrayList<Node_temp>> graph = new ArrayList<ArrayList<Node_temp>>();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            graph.add(new ArrayList<Node_temp>());
        }

        graph.get(0).add(new Node_temp(1, 7));
        graph.get(0).add(new Node_temp(2, 5));

        graph.get(1).add(new Node_temp(0, 7));
        graph.get(2).add(new Node_temp(0, 5));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                graph.get(i).get(j).show();
            }
            System.out.println();
        }
    }
}

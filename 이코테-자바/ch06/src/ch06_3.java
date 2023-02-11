import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Student implements Comparable<Student>{
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Student o) {
        if (this.score < o.score) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}

public class ch06_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students= new ArrayList<>();

        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            String[] s1 = s.split(" ");
            students.add(new Student(s1[0], Integer.parseInt(s1[1])));
        }

        students.sort(Comparator.reverseOrder());

        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getName());
        }
    }
}

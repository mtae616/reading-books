import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Student implements Comparable<Student> {
    private String name;
    private int lang;
    private int eng;
    private int math;

    @Override
    public int compareTo(Student other) {
        if (this.lang == other.lang && this.eng == other.eng && this.math == other.math) {
            return this.name.compareTo(other.name);
        }
        if (this.lang == other.lang && this.eng == other.eng) {
            return Integer.compare(other.math, this.math);
        }
        if (this.lang == other.lang) {
            return Integer.compare(this.eng, other.eng);
        }
        return Integer.compare(other.lang, this.lang);
    }

    public Student(String name, int lang, int eng, int math) {
        this.name = name;
        this.lang = lang;
        this.eng = eng;
        this.math = math;
    }
    public String getName() { return name; }
}

public class ch14_23 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        List<Student> temp = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] s = bf.readLine().split(" ");
            temp.add(new Student(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3])));
        }

        Collections.sort(temp);
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(temp.get(i).getName());
        }
        bf.close();
    }
}
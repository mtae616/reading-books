package Streams2;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ReportCard {
    private int kor;
    private int eng;
    private int math;

    public ReportCard(int kor, int eng, int math) {
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

    public int getKor() {
        return kor;
    }

    public int getEng() {
        return eng;
    }

    public int getMath() {
        return math;
    }
}

public class GradeAverage {
    public static void main(String[] args) {
        ReportCard[] cards = {new ReportCard(70, 80, 90), new ReportCard(90, 80, 70), new ReportCard(80, 80, 80)};

        // ReportCard 인스턴스로 이뤄진 스트림 생성
//        Stream<ReportCard> sr = Arrays.stream(cards);
//        IntStream si = sr.flatMapToInt(r -> IntStream.of(r.getKor(), r.getEng(), r.getMath()));
//
//        double avg = si.average().getAsDouble();
//        System.out.println("avg. " + avg);

        Arrays.stream(cards).flatMapToInt(r -> IntStream.of(r.getKor(), r.getEng(), r.getMath())).average().ifPresent(avg -> System.out.println("avg. " + avg));

        System.out.println();

        Arrays.stream(cards).mapToDouble(r -> (r.getKor() + r.getEng() + r.getMath()) / 3).forEach(e -> System.out.println(e));
    }
}

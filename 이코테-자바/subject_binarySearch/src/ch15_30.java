import java.util.ArrayList;
import java.util.Collections;

public class ch15_30 {

    public static ArrayList<ArrayList<String>> wordArr = new ArrayList<>();
    public static ArrayList<ArrayList<String>> wordArrRev = new ArrayList<>();

    public static int lowerBound(ArrayList<String> arr, String target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr.get(mid).compareTo(target) >= 0) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    public static int upperBound(ArrayList<String> arr, String target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr.get(mid).compareTo(target) > 0) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    public static int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        for (int i = 0; i < 10001; i++) {
            wordArr.add(new ArrayList<>());
            wordArrRev.add(new ArrayList<>());
        }

        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            wordArr.get(len).add(words[i]);
            wordArrRev.get(len).add(new StringBuilder(words[i]).reverse().toString());
        }


        for (int i = 0; i < 10001; i++) {
            Collections.sort(wordArr.get(i));
            Collections.sort(wordArrRev.get(i));
        }

        for (int i = 0; i < queries.length; i++) {
            if (queries[i].startsWith("?")) {
                ArrayList<String> now = wordArrRev.get(queries[i].length());
                String reversed = new StringBuilder(queries[i]).reverse().toString();
                int start = lowerBound(now, reversed.replace('?', 'a'), 0,now.size());
                int end = lowerBound(now, reversed.replace('?', 'z'), 0,now.size());
                answer[i] = end - start;
            } else {
                ArrayList<String> now = wordArr.get(queries[i].length());
                int start = lowerBound(now, queries[i].replace('?', 'a'), 0,now.size());
                int end = lowerBound(now, queries[i].replace('?', 'z'), 0,now.size());
                answer[i] = end - start;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        solution(words, queries);
    }
}

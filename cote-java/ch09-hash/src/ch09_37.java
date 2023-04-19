import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ch09_37 {
    static class Solution {
        public static String solution(String[] participant, String[] completion) {
            Arrays.sort(participant);
            Arrays.sort(completion);
            for (int i = 0; i < participant.length; i++) {
                if (i >= completion.length || !participant[i].equals(completion[i])) return participant[i];
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Solution.solution(new String[]{
                        "leo", "kiki", "eden"
                },
                new String[]{
                        "eden", "kiki"
                });
    }
}

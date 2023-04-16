import java.util.*;

public class ch06_22 {
    static class Solution {
        public static int solution(String[] user_id, String[] banned_id) {

            Map<Map<Integer, String>, List<String>> banned = new HashMap<>();
            for (int i = 0; i < banned_id.length; i++) {
                List<Integer> equalList = new ArrayList<>();
                for (int j = 0; j < user_id.length; j++) {
                    boolean isEqual = true;
                    if (banned_id[i].length() == user_id[j].length()) {
                        for (int k = 0; k < banned_id[i].length(); k++) {
                            if (banned_id[i].charAt(k) == '*') continue;
                            if (banned_id[i].charAt(k) != user_id[j].charAt(k)) isEqual = false;
                        }
                        if (isEqual) equalList.add(j);
                    }
                }

                Map<Integer, String> key = new HashMap<>();
                key.put(i, banned_id[i]);
                for (int j = 0; j < equalList.size(); j++) {
                    List<String> banList = banned.get(key);
                    if (j == 0) {
                        List<String> value = new ArrayList<>();
                        value.add(user_id[equalList.get(j)]);
                        banned.put(key, value);
                    } else {
                        int idx = banList.indexOf(user_id[equalList.get(j)]);
                        if (idx == -1) banList.add(user_id[equalList.get(j)]);
                    }
                }
            }
            List<List<String>> arr = new ArrayList<>(banned.values());
            List<String> answerList = new ArrayList<>();
            getCombi(0, banned.keySet().size(), arr, new ArrayList<>(), answerList);
            return answerList.size();
        }

        public static void getCombi(int iter, int size, List<List<String>> arr, List<String> now, List<String> answers) {
            if (size == now.size()) {
                Collections.sort(now);
                if (!answers.contains(now.toString())) {
                    answers.add(now.toString());
                }
                return;
            }

            List<String> strings = arr.get(iter);
            for (int i = 0; i < strings.size(); i++) {
                if (!now.contains(strings.get(i))) {
                    now.add(strings.get(i));
                    getCombi(iter + 1, size, arr, now, answers);
                    now.remove(strings.get(i));
                }
            }
        }


    }

    public static void main(String[] args) {
        Solution.solution(new String[]{
                        "frodo", "fradi", "crodo", "abc123", "frodoc"
                },
                new String[]{
                        "*rodo", "*rodo", "******"
                });
    }
}

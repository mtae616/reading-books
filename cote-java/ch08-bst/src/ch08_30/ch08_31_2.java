package ch08_30;

import java.util.*;

public class ch08_31_2 {
    static class Solution {
        public static Map<String, List<Integer>> map = new HashMap<>();

        public static void make(String[] origin, String temp, int iter, int score) {
            if (iter == origin.length - 1) {
                map.putIfAbsent(temp, new ArrayList<>());
                map.get(temp).add(score);
                return;
            }
            make(origin, temp + origin[iter], iter + 1, score);
            make(origin, temp + "-", iter + 1, score);
        }

        public static int bst(int score, List<Integer> scores) {
            int start = 0;
            int end = scores.size() - 1;

            while (start < end) {
                int mid = (start + end) / 2;
                if (scores.get(mid) >= score) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }
            if (scores.get(start) < score) {
                return scores.size();
            }
            return start;
        }

        public static int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];

            // 언어 직군 경력 소울푸드 점수
            for (String s : info) {
                String[] s1 = s.split(" ");
                make(s1, "", 0, Integer.parseInt(s1[s1.length - 1]));
            }
            for (List<Integer> list : map.values()) Collections.sort(list);
            int idx = 0;
            for (String q : query) {
                String[] tokens = q.split(" (and )?");
                String key = String.join("", Arrays.copyOf(tokens, tokens.length - 1));
                if (!map.containsKey(key)) {
                    answer[idx++] = 0;
                    continue;
                }
                int score = Integer.parseInt(tokens[tokens.length - 1]);
                List<Integer> scores = map.get(key);
                answer[idx++] = scores.size() - bst(score, scores);
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution.solution(new String[]{
                        "java backend junior pizza 150",
                        "python frontend senior chicken 210",
                        "python frontend senior chicken 150",
                        "cpp backend senior pizza 260",
                        "java backend junior chicken 80",
                        "python backend senior chicken 50"
                },
                new String[]{
                        "java and backend and junior and pizza 100",
                        "python and frontend and senior and chicken 200",
                        "cpp and - and senior and pizza 250",
                        "- and backend and senior and - 150",
                        "- and - and - and chicken 100",
                        "- and - and - and - 150"
                });
    }
}

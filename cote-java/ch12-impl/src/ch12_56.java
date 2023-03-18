//class Solution {
//    public static int solution(String skill, String[] skill_trees) {
//        int answer = 0;
//
//        char[] skills = skill.toCharArray();
//
//
//        for (int i = 0; i < skill_trees.length; i++) {
//            int skIdx = 0;
//            boolean flag = true;
//            for (int j = 0; j < skill_trees[i].length(); j++) {
//                int idx = skill.indexOf(skill_trees[i].charAt(j));
//                if (idx >= 0 && skills[skIdx] != skill_trees[i].charAt(j)) {
//                    flag = false;
//                    break;
//                } else if (idx >= 0 && skills[skIdx] == skill_trees[i].charAt(j)) skIdx += 1;
//                if (skIdx == skill.length()) break;
//            }
//            if (flag) answer += 1;
//        }
//        return answer;
//    }
//}

import java.util.Arrays;
import java.util.List;

class Solution {
    public static int solution(String skill, String[] skill_trees) {
        return (int) Arrays.stream(skill_trees)
                .map(s -> s.replaceAll("[^" + skill + "]", ""))
                .filter(skill::startsWith)
                .count();
    }
}

public class ch12_56 {
    public static void main(String[] args) {
        Solution.solution("CBD", new String[]{
                "BACDE", "CBADF", "AECB", "BDA"
        });
    }
}

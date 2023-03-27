import java.util.HashMap;

public class ch04_7 {
    static class Solution {
        public static String solution(String s) {
            StringBuilder ans = new StringBuilder();

            int idx = 1;
            for (int i = 0; i < s.length(); i++) {
                if (idx % 2 == 1) ans.append(Character.toUpperCase(s.charAt(i)));
                else ans.append(Character.toLowerCase(s.charAt(i)));

                if (Character.isAlphabetic(s.charAt(i))) idx += 1;
                else idx = 1;
            }
            return ans.toString();
        }
    }


    public static void main(String[] args) {
//        Solution.solution("TrY HeLlO WoRlD ");

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        while (true) {
            objectObjectHashMap.put("key", new Object());
        }
    }
}

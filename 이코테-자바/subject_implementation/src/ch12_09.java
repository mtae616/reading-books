public class ch12_09 {
    public static int solution(String s) {
        int answer = (int) 1e9;

        for (int i = 1; i < s.length(); i++) {
            String temp = "";

            int j = 0;
            int cnt = 1;
            String buf = "";
            while (j < s.length()) {
                String now = s.substring(j, Math.min(s.length(), j + i));
                if (now.equals(buf)) cnt += 1;
                else if (cnt == 1){
                    temp += buf;
                    buf = now;
                    cnt = 1;
                } else {
                    temp += cnt;
                    temp += buf;
                    buf = now;
                    cnt = 1;
                }

                j += i;
            }
            if (cnt != 1) temp += cnt;
            temp += buf;

            answer = Math.min(temp.length(), answer);
        }

        answer = Math.min(answer, s.length());
        return answer;
    }

    public static void main(String[] args) {
        solution("a");
    }
}

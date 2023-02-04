import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ch03_1 {
    public static void main(String[] args) {
        // 500, 100, 50, 10
        int n = 1260;
        int cnt = 0;
        while (n > 0) {
            if (n / 500 > 0) {
                cnt += n / 500;
                n -= (n / 500) * 500;
            } else if (n / 100 > 0) {
                cnt += n / 100;
                n -= (n / 100) * 100;
            } else if (n / 50 > 0) {
                cnt += n / 50;
                n -= (n / 50) * 50;
            } else {
                cnt += n / 10;
                n -= (n / 10) * 10;
            }
        }

        AtomicInteger n2 = new AtomicInteger(1260);
        AtomicInteger cnt2 = new AtomicInteger();
        int[] coins = {500, 100, 50, 10};

        Arrays.stream(coins).forEach(e -> {
            cnt2.addAndGet(n2.get() / e);
            n2.updateAndGet(v -> v % e);
        });

        System.out.println(cnt);
    }
}
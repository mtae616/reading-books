package step1.pricing;

import step1.DiscountPolicy;
import step1.Money;
import step1.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {
    @Override
    protected Money getDiscountAmount(Screening Screening) {
        return Money.ZERO;
    }
}

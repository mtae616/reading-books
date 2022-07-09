package MethodReferencesAndOptional;

import java.util.Optional;

class ContInfo2 {
    Optional<String> phone;
    Optional<String> adrs;

    public ContInfo2(Optional<String> phone, Optional<String> adrs) {
        this.phone = phone;
        this.adrs = adrs;
    }

    public Optional<String> getPhone() {
        return phone;
    }

    public Optional<String> getAdrs() {
        return adrs;
    }
}

public class FlatMapElseOptional {
    public static void main(String[] args) {
        Optional<ContInfo2> ci = Optional.of(new ContInfo2(Optional.ofNullable(null), Optional.of("Republic korea")));
        String phone = ci.flatMap(c -> c.getPhone()).orElse("There is no phone number");
        String adrs = ci.flatMap(c -> c.getAdrs()).orElse("there is no adrs");

        System.out.println(phone);
        System.out.println(adrs);
    }
}

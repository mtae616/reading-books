package MethodReferencesAndOptional;

import javax.swing.text.html.Option;
import java.util.Optional;

class Friend {
    String name;
    Optional<Company> cmp;

    public Friend(String name, Optional<Company> cmp) {
        this.name = name;
        this.cmp = cmp;
    }

    public String getName() {
        return name;
    }

    public Optional<Company> getCmp() {
        return cmp;
    }
}

class Company {
    String cName;
    Optional<ContInfo> contInfo;

    public String getcName() {
        return cName;
    }

    public Optional<ContInfo> getContInfo() {
        return contInfo;
    }

    public Company(String cName, Optional<ContInfo> contInfo) {
        this.cName = cName;
        this.contInfo = contInfo;
    }
}

class ContInfo {
    Optional<String> phone;
    Optional<String> adrs;

    public ContInfo(Optional<String> phone, Optional<String> adrs) {
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

public class NullPointerCaseStudy3 {
    public static void showCompAddr(Optional<Friend> f) {
        String addr = f.flatMap(Friend::getCmp).flatMap(Company::getContInfo).flatMap(ContInfo::getAdrs).orElse("There's no address information");
        System.out.println(addr);
    }
    public static void main(String[] args) {
        Optional<ContInfo> ci = Optional.of(new ContInfo(Optional.ofNullable(null), Optional.of("Republic of Korea")));
        Optional<Company> cp = Optional.of(new Company("YaHo Co., Ltd.", ci));
        Optional<Friend> frn = Optional.of(new Friend("LEE SU", cp));
        showCompAddr(frn);
    }
}

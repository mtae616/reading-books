//package MethodReferencesAndOptional;
//
//import java.util.Optional;
//
//class Friend {
//    String name;
//    Company cmp; // null 일 수 있음
//
//    public Friend(String name, Company cmp) {
//        this.name = name;
//        this.cmp = cmp;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Company getCmp() {
//        return cmp;
//    }
//}
//
//class Company {
//    String cName;
//    ContInfo cInfo;
//
//    public Company(String cName, ContInfo cInfo) {
//        this.cName = cName;
//        this.cInfo = cInfo;
//    }
//    public String getcName() {
//        return cName;
//    }
//
//    public ContInfo getcInfo() {
//        return cInfo;
//    }
//}
//
//class ContInfo {
//    String phone;
//    String adrs;
//
//    public ContInfo(String phone, String adrs) {
//        this.phone = phone;
//        this.adrs = adrs;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public String getAdrs() {
//        return adrs;
//    }
//}
//
//public class NullPointerCaseStudy {
//    public static void showCompAddr(Optional<Friend> f) {
//        String addr = f.map(Friend::getCmp).map(Company::getcInfo).map(ContInfo::getAdrs).orElse(("thre's no address information."));
//
//        System.out.println(addr);
//    }
//    public static void main(String[] args) {
//        ContInfo ci = new ContInfo("321-444-577", "Republic of Korea");
//        Company cp = new Company("Yaho Co., Ltd", ci);
//        Friend frn = new Friend("LEE SU", cp);
//        showCompAddr(Optional.of(frn));
//
//    }
//}

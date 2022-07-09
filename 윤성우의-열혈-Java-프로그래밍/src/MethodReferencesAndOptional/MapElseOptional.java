//package MethodReferencesAndOptional;
//
//import java.util.Optional;
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
//public class MapElseOptional {
//    public static void main(String[] args) {
//        Optional<ContInfo> ci = Optional.of(new ContInfo(null, "Republic of Korea"));
//        String phone = ci.map(c -> c.getPhone()).orElse("There is no phone number");
//        String addr = ci.map(c -> c.getAdrs()).orElse("There is no address");
//
//        System.out.println(phone);
//        System.out.println(addr);
//    }
//}

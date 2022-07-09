package EnumAndVariadicAndAnnotation;

public class AtDeprecated {
    public static void main(String[] args) {
        // @Deprecated 다른 것으로 대체되어 필요 없는 거
        // deprecated 된 메소드 사용하면 컴파일은 되는데 메세지 받음
        // 그 떄 @SuppressWarnings("deprecation") 으로 메세지 지움
    }
}

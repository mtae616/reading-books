package LambdaExpression;

interface HowLong {
    int len(String s);    // ���� ��ȯ�ϴ� �޼ҵ�
}

class OneParamAndReturn { 
    public static void main(String[] args) {
        HowLong hl = s -> s.length();
        System.out.println(hl.len("I am so happy"));
    }
}
package LambdaExpression;

interface Printable {
    void print(String s);    // �Ű����� �ϳ�, ��ȯ�� void
}

class OneParamNoReturn {
    public static void main(String[] args) {
        Printable p;

        p = (String s) -> {System.out.println(s);};    // ���� ���� ǥ��
        p.print("Lambda exp one.");

        p = (String s) -> System.out.println(s);    // �߰�ȣ ����
        p.print("Lambda exp two.");
        
        p = (s) -> System.out.println(s);    // �Ű����� �� ����
        p.print("Lambda exp three.");

        p = s -> System.out.println(s);    // �Ű����� �Ұ�ȣ ����
        p.print("Lambda exp four.");
    }
}
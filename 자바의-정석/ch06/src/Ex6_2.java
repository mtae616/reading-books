public class Ex6_2 {
    static class SutdaCard {
        int a;
        boolean b;

        public SutdaCard(int a, boolean b) {
            this.a = a;
            this.b = b;
        }
        public SutdaCard() {}

        public void info() {
            if (this.a == 0) {
                System.out.println("1K");
            } else {
                System.out.println(this.a);
            }
        }

    }
    public static void main(String[] args) {
        SutdaCard card1 = new SutdaCard(3, false);
        SutdaCard card2 = new SutdaCard();
        card1.info();
        card2.info();
    }
}
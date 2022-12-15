class SutdaDeck {
    final int CARD_NUM = 20;
    SutdaCard[] cards = new SutdaCard[CARD_NUM];
    SutdaDeck() {
        for (int i = 0; i < CARD_NUM; i++) {
            int temp = i%10+1;
            if (temp == 1 || temp == 3 || temp == 8) {
                cards[i] = new SutdaCard(temp, true);
            } else {
                cards[i] = new SutdaCard(temp, false);
            }
        }
    }

    public void shuffle() {
        for (int i = 0; i < CARD_NUM; i++) {
            int temp_idx = (int) (Math.random() * cards.length);
            SutdaCard tmp;
            tmp = cards[i];
            cards[i] = cards[temp_idx];
            cards[temp_idx] = tmp;
        }
    }

    public SutdaCard pick(int idx) {
        return cards[idx];
    }

    public SutdaCard pick() {
        return cards[(int) (Math.random() * cards.length)];
    }
}
class SutdaCard {
    int num;
    boolean isKwang;
    SutdaCard() {
        this(1, true);
    }
    SutdaCard(int num, boolean isKwang) {
        this.num = num;
        this.isKwang = isKwang;
    }
    public String toString() {
        return num + ( isKwang ? "K":"");
    }
}

public class Ex7_1 {
    public static void main(String[] args) {
        SutdaDeck deck = new SutdaDeck();
        System.out.println(deck.pick(0));
        System.out.println(deck.pick());
        deck.shuffle();
        for(int i=0; i < deck.cards.length;i++)
            System.out.print(deck.cards[i]+",");
        System.out.println();
        System.out.println(deck.pick(0));
    }
}
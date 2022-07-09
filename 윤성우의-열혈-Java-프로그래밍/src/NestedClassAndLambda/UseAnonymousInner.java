package NestedClassAndLambda;

interface Playable {
    void print();
}

class Player {
    private String con;

    public Player(String con) {
        this.con = con;
    }

    public Playable getPlayer() {
        return new Playable() {
            public void print() {
                System.out.println(con);
            }
        };
    }
}

public class UseAnonymousInner {
    public static void main(String[] args) {
        Player p = new Player("농구 선수");
        Playable prn = p.getPlayer();
        prn.print();
    }
}

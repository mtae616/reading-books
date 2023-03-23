public class Deadlock {
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void bow(Friend bower) {
            System.out.println("bow : " + bower.getName());
            bower.bowBack(bower);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.println("bow back : " + bower.getName());
            bower.bow(bower);
        }
    }
    public static void main(String[] args) {
        Friend alphonse = new Friend("Alphonse");
        Friend gaston = new Friend("Gaston");

        new Thread(new Runnable() {
            @Override
            public void run() {
                alphonse.bow(gaston);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                gaston.bow(alphonse);
            }
        }).start();

    }
}

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Flow;

public class PubSub {
    public static void main(String[] args) {
        Iterator<Integer> iterator = List.of(1, 2, 3, 4, 5).iterator();

        Flow.Publisher<Object> publisher = new Flow.Publisher<>() {
            @Override
            public void subscribe(Flow.Subscriber<? super Object> subscriber) {
                subscriber.onSubscribe(new Flow.Subscription() {
                    @Override
                    public void request(long n) {
                        iterator.forEachRemaining(subscriber::onNext);
                        subscriber.onComplete();
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };

        publisher.subscribe(new Flow.Subscriber<>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("PubSub.onSubscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Object item) {
                System.out.println("item = " + item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("PubSub.onError");
            }

            @Override
            public void onComplete() {
                System.out.println("PubSub.onComplete");
            }
        });

    }

}

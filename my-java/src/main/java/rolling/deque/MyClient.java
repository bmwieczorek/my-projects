package rolling.deque;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class MyClient {

    public static void main(String[] args) {
        Deque<String> deck = new RollingDeque<String>(new LinkedBlockingDeque<String>(5));
        System.out.println(deck.offer("one"));
        System.out.println(deck.offer("two"));
        System.out.println(deck.offer("three"));
        System.out.println(deck.offer("four"));
        System.out.println(deck.offer("five"));
        System.out.println(deck);
        System.out.println(deck.offer("six"));
        System.out.println(deck);
        System.out.println(deck.offer("seven"));
        System.out.println(deck);
    }
}

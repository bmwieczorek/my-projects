package rolling.deque;

import java.util.Deque;

public class RollingDeque<E> extends ForwardingDeque<E> {

    public RollingDeque(Deque<E> deque) {
        super(deque);
    }

    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }

    @Override
    public boolean offerLast(E e) {
        if (super.offerLast(e)) {
            return true;
        }
        super.pollFirst();
        return super.offerLast(e);
    }

}

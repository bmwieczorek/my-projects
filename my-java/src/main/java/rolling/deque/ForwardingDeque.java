package rolling.deque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

class ForwardingDeque<E> implements Deque<E> {

    private final Deque<E> deque;

    public ForwardingDeque(Deque<E> deque) {
        this.deque = deque;
    }

    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    @Override
    public Object[] toArray() {
        return deque.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return deque.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return deque.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return deque.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return deque.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return deque.retainAll(c);
    }

    @Override
    public void clear() {
        deque.clear();
    }

    @Override
    public E removeFirst() {
        return deque.removeFirst();
    }

    @Override
    public E removeLast() {
        return deque.removeLast();
    }

    @Override
    public E pollFirst() {
        return deque.pollFirst();
    }

    @Override
    public E pollLast() {
        return deque.pollLast();
    }

    @Override
    public E getFirst() {
        return deque.getFirst();
    }

    @Override
    public E getLast() {
        return deque.getLast();
    }

    @Override
    public E peekFirst() {
        return deque.peekFirst();
    }

    @Override
    public E peekLast() {
        return deque.peekLast();
    }

    @Override
    public E pop() {
        return deque.pop();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return deque.descendingIterator();
    }

    @Override
    public void addFirst(E e) {
        deque.addFirst(e);
    }

    @Override
    public void addLast(E e) {
        deque.addLast(e);
    }

    @Override
    public boolean offerFirst(E e) {
        return deque.offerFirst(e);
    }

    @Override
    public boolean offerLast(E e) {
        return deque.offerLast(e);
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return deque.removeFirstOccurrence(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return deque.removeLastOccurrence(o);
    }

    @Override
    public boolean add(E e) {
        return deque.add(e);
    }

    @Override
    public boolean offer(E e) {
        return deque.offer(e);
    }

    @Override
    public E remove() {
        return deque.remove();
    }

    @Override
    public E poll() {
        return deque.poll();
    }

    @Override
    public E element() {
        return deque.element();
    }

    @Override
    public E peek() {
        return deque.peek();
    }

    @Override
    public boolean remove(Object o) {
        return deque.remove(o);
    }

    @Override
    public boolean contains(Object o) {
        return deque.contains(o);
    }

    @Override
    public int size() {
        return deque.size();
    }

    @Override
    public Iterator<E> iterator() {
        return deque.iterator();
    }

    @Override
    public void push(E e) {
        deque.push(e);

    }

    @Override
    public String toString() {
        return deque.toString();
    }

    @Override
    public int hashCode() {
        return deque.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return deque.equals(obj);
    }
}
package innerinterfaces;

public interface Outer {

    void outsing();

    interface Inner {
        <K, V> K insing(V v);
    }

}

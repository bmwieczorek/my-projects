package callback;

public interface CallbackMapper<T> {
    T map(MySet<T> src);
}

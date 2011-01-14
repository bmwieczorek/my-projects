package mock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class MyMock<V> {

    private static Map<Key, Object> map = new HashMap<Key, Object>();
    private static Key currentKey;

    private static class MyInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            currentKey = new Key(method, args);
            return map.get(currentKey);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T mock(final Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] { clazz }, new MyInvocationHandler());
    }

    public static <V> MyMock<V> verify(@SuppressWarnings("unused") V v) {
        return new MyMock<V>();
    }

    public void thenReturn(V v) {
        map.put(currentKey, v);
    }
}

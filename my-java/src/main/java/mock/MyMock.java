package mock;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class MyMock<V> {

    private static Map<Key, Object> map = new HashMap<Key, Object>();
    private static Key currentKey;
    private static boolean isVerify;

    private static class MyInvocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            currentKey = new Key(method, args);
            if (isVerify) {
                assertTrue("Expected "+ currentKey, map.containsKey(currentKey));
                isVerify = false; 
            }
            return map.get(currentKey);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T mock(final Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] { clazz }, new MyInvocationHandler());
    }

    public static <V> MyMock<V> when(Object o) {
        System.out.println("In when for " + o);
        return new MyMock<V>();
    }

    public void thenReturn(V v) {
        System.out.println("In thenReturn");
        map.put(currentKey, v);
    }

    public static <V> V verify(V v) {
        System.out.println("In verify");
        isVerify = true;
        return v;
    }
}

package mock;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyMock<V> {

	static Object value = null;

	static class MyInvocationHandler implements InvocationHandler {

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return value;
		}
	};

	@SuppressWarnings("unchecked")
	public static <T> T mock(final Class<T> clazz) {
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] { clazz }, new MyInvocationHandler());
	}

	public static <V> MyMock<V> verify(V v) {
		return new MyMock<V>();
	}

	public void thenReturn(V v) {
		value = v;
	}
}

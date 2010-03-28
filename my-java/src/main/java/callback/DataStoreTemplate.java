package callback;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DataStoreTemplate {
	static class MyHashSet<T> extends HashSet<T> implements MySet<T> {

		private static final long serialVersionUID = 1L;

		public MyHashSet(Collection<? extends T> t) {
			super(t);
		}

		public MyHashSet() {
		}

		public int getInt(String name) {
			T result = iterator().next();
			Class<? extends Object> class1 = result.getClass();
			Field field = null;
			try {
				field = class1.getDeclaredField(name);
				return (Integer) field.get(result);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			return 0;
		}

		public String getString(String name) {
			T result = iterator().next();
			Class<? extends Object> class1 = result.getClass();
			Field field = null;
			try {
				field = class1.getDeclaredField(name);
				return (String) field.get(result);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T queryForObject(CallbackMapper<T> callbackMapper) {

		Person person = new Person("wacek", 28);
		Set<T> src = new MyHashSet<T>(Arrays.asList((T) person));

		for (T t : src) {
			return callbackMapper.map(new MyHashSet<T>(Arrays.asList(t)));
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public <T> Collection<T> query(CallbackMapper<T> callbackMapper) {

		Person person = new Person("kasia", 26);
		Person person2 = new Person("ania", 27);
		Set<T> src = new MyHashSet<T>(Arrays.asList((T) person, (T) person2));

		Collection<T> dst = new MyHashSet<T>();

		for (T t : src) {
			dst.add(callbackMapper.map(new MyHashSet<T>(Arrays.asList(t))));
		}
		return dst;
	}

}

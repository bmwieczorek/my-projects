package generics.classvsstaticmethod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class A<T> {
}

class B<T extends Number> {
}
interface Comparable<T> {
	int compare(T t);
}

class D<T extends Comparable<T>> {
}
// class Y<?> {} // doesn't compile
// class Z<? extends Number> {} //doesn't compile

class Utils {
	public static <T> T max(List<T> list) {
		return null; // impl omitted
	}

	public static <T extends Comparable<T>> T max(List<? extends T> list) {
		return null; // impl omitted
	}

	// generic static factory method
	public static <K, V> Map<K, V> createHashMap() {
		return new HashMap<K, V>();
	}
}

public class Example {
	public static void main(String[] args) {

		// explicitly specify the value of type parameter when invoking generic constructors
		A<String> a = new A<String>();
		B<Integer> b = new B<Integer>();

		// the value of type parameter is figured out by examining types of method arguments
		List<Integer> integers = Arrays.asList(1, 2, 3);
		Integer max = Utils.max(integers);
	}
}
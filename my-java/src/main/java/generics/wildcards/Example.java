package generics.wildcards;

import java.util.ArrayList;
import java.util.List;

public class Example {
	static void pushAll(List<? extends Number> list) {
	}

	static void popAll(List<? super Number> list) {
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// List<Number> list = new ArrayList<Integer>(); // doesn't compile
		List<? extends Number> list = new ArrayList<Integer>(); // compiles
		List<? super Number> list2 = new ArrayList<Object>(); // compiles

		List<Integer> ints = new ArrayList<Integer>();
		pushAll(ints);
		List<Object> objs = new ArrayList<Object>();
		popAll(objs);
	}

}

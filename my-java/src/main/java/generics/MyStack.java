package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class MyStack<E> {

	private Stack<E> stack = new Stack<E>();

	void pushAll(Collection<? extends E> inElements) {
		for (E e : inElements) {
			stack.push(e);
		}
	}

	void popAll(Collection<? super E> outElements) {
		while (!stack.isEmpty()) {
			outElements.add(stack.pop());
		}
	}

	public static void main(String[] args) {
		MyStack<Number> myStack = new MyStack<Number>();
		// MyStack<Integer> myStack = new MyStack<Integer>();
		List<Integer> inElements = Arrays.asList(1, 2, 3);
		// System.out.println(inElements);

		List<Number> numbers = new ArrayList<Number>();
		numbers.add(new Integer(1));
		numbers.add(new Integer(1));

		myStack.pushAll(inElements);

		// List<Integer> outElements = new ArrayList<Integer>();
		// List<Object> outElements = new ArrayList<Object>();
		List<Number> outElements = new ArrayList<Number>();
		myStack.popAll(outElements);

		// System.out.println(outElements);
	}
}

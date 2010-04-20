package generics.returnnowildcards;

import generics.Car;
import generics.Toyota;

import java.util.Arrays;
import java.util.List;

public class Example {
	static <T> List<T> merge(List<T> list1, List<T> list2) {
		return list2; // impl omitted
	}

	static <T> List<? extends T> merge2(List<? extends T> list1, List<? extends T> list2) {
		return list2; // impl omitted
	}

	@SuppressWarnings("unchecked")
	static <T> List<T> merge3(List<? extends T> list1, List<? extends T> list2) {
		return (List<T>) list2; // impl omitted
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<Car> cars = Arrays.asList(new Car(), new Toyota());
		List<Toyota> toyotas = Arrays.asList(new Toyota(), new Toyota());

		// List<Car> cars2 = toyotas; // compilation failure

		// merge(cars, toyotas); // compilation failure

		List<? extends Car> merge2 = merge2(cars, toyotas);

		List<Car> merge3 = merge3(cars, toyotas);

		// List<Car> merge33 = merge3(toyotas, toyotas); // compilation failure

		List<Car> merge333 = Example.<Car> merge3(toyotas, toyotas);

	}
}

package generics.example;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Example {

	public static <T extends Car> List<T> compareCars(List<T> cars) {

		Comparator<? super T> comparator = new Comparator<T>() {
			@Override
			public int compare(T t1, T t2) {
				return t1.compare(t2);
			}
		};

		Collections.sort(cars, comparator);
		return cars;
	}

	public static <T extends Comparable<? super T>> List<T> compare(List<T> elements) {
		Comparator<? super T> comparator = new Comparator<T>() {
			@Override
			public int compare(T t1, T t2) {
				return t1.compare(t2);
			}
		};
		Collections.sort(elements, comparator);
		return elements;
	}

	public static void main(String[] args) {
		Car car = new Car();
		car.price = 1;
		Toyota toyota = new Toyota();
		toyota.price = 2;
		Audi audi = new Audi();
		audi.price = 3;

		List<Car> cars = Arrays.asList(audi, car, toyota);
		List<Car> comparedCars = compareCars(cars);
		System.out.println(comparedCars);

		List<Car> elements = Arrays.asList(audi, car, toyota);
		List<Car> comparedElements = compareCars(elements);
		System.out.println(comparedElements);
		
		// compare(elements2);
	}
}

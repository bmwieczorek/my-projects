package generics;

public class Car implements Comparable<Car> {

	int price;

	@Override
	public int compare(Car car) {
		return (car.price == this.price) ? 0 : (car.price > this.price) ? 1 : -1;
	}

	public static void main(String[] args) {
		Car c1 = new Car();
		c1.price = 2;
		Car c2 = new Car();
		c2.price = 1;

		int compare = c1.compare(c2);
		System.out.println(compare);

	}

	@Override
	public String toString() {
		return getClass().getName() + ":" + price;
	}
}

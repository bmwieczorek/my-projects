package statickeyword;

class Car {
	static void drive() {
		System.out.println("Car drive");
	}
}

class Audi extends Car {
	static void drive() {
		System.out.println("Audi drive");
	}
}

class Dog extends Animal {
}

class Animal {
	static void makeNoise() {
		System.out.println("Animal noise");
	}
}

public class StaticNoPolymorphicCallExample {

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        
        //proper usage
        Car.drive();
        Audi.drive();
        
        //not recommended
        Car car = new Car();
        Audi audi = new Audi();
        car.drive(); // car
        audi.drive(); // audi

        Car c = new Audi();
        c.drive(); // car - no polimorphism

        //inherited from Animal
        Dog.makeNoise(); // dog does not have explicitly written makeNoise

    }

}

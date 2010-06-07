package designpatterns.factorymethod;

interface Product {
}

class Car implements Product {
    public Car() {
        System.out.println("Just created a car");
    }
}

interface Factory {
    Product create();
}

class CarFactory implements Factory {
    public Product create() {
        return new Car();
    }
}

public class Example {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Factory carFactory = new CarFactory();
        Product car = carFactory.create();
    }
}

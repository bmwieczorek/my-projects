package statickeyword;

public class RunExample {

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

class Car {
    int x;

    public Car(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Car=" + x;
    }
}

class MyOuter {
    private int i = 7;
    private Car c = new Car(77);

    @Override
    public String toString() {
        return "i=" + i + "," + c;
    }

    class MyInner {
        public Car getCar() {
            return c;
        }

        public void setCar(Car car) {
            c = car;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            MyOuter.this.i = i;
        }
    }
}

public class DangerousInnerClass {
    public static void main(String[] args) {
        MyOuter myOuter = new MyOuter();
        MyOuter.MyInner myInner = myOuter.new MyInner();
        System.out.println(myOuter);
        Car car = myInner.getCar();
        car.x = 55;
        System.out.println(myOuter);
        myInner.setCar(new Car(123));
        System.out.println(myOuter);
        myInner.setI(987);
        System.out.println(myOuter);

    }
}

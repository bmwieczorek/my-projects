package calccmd;

public class Calc {
    public Calc() {
        System.err.println("Calc constr invoked");
    }

    int value = 0;

    int add(int newValue) {
        value += newValue;
        System.out.println(value);
        return value;
    }

    int substract(int newValue) {
        value -= newValue;
        System.out.println(value);
        return value;
    }
}

package calccmd;

import static calccmd.CalcCmd.Operation.plus;

public class Example {

    public static void main(String[] args) {
        Caller c = new Caller();
        Caller c1 = new Caller();
        c.calculate(plus, 1);
        c1.calculate(plus, 1000);
        c.calculate(plus, 10);
        c1.calculate(plus, 11000);
        c.redo();
        c1.redo();
    }
}

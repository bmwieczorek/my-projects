package calccmd;

public class CalcCmd implements Cmd {

    public enum Operation {
        plus, minus;
    }

    Calc calc = new Calc();
    private Operation operation;

    private int value;

    public CalcCmd(Operation operation, int value) {
        this.operation = operation;
        this.value = value;
    }

    public void execute() {
        switch (operation) {
        case plus: {
            calc.add(value);
            break;
        }
        case minus: {
            calc.substract(value);
            break;
        }
        default:
            throw new RuntimeException("Unsupported operation: " + operation);
        }
    }

    public void unexecute() {
        switch (operation) {
        case plus: {
            calc.substract(value);
            break;
        }
        case minus: {
            calc.add(value);
            break;
        }
        default:
            throw new RuntimeException("Unsupported operation: " + operation);
        }
    }

}

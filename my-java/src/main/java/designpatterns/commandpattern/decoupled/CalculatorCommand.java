package designpatterns.commandpattern.decoupled;


public class CalculatorCommand implements Command {

    private final CalculatorReceiver calculatorReceiver;
    private final int value;
    private OperationType operationType;

    public CalculatorCommand(OperationType operationType, int value, CalculatorReceiver calculatorReceiver) {
        this.operationType = operationType;
        this.value = value;
        this.calculatorReceiver = calculatorReceiver;
    }

    public int execute() {
        switch (operationType) {
        case add:
            return calculatorReceiver.add(value);
        case substract:
            return calculatorReceiver.substract(value);
        default:
            throw new RuntimeException("Unsupported operation: " + operationType);
        }
    }

    public int unexecute() {
        revertOperation();
        return execute();
    }

    private void revertOperation() {
        switch (operationType) {
        case add:
            operationType = OperationType.substract;
            break;
        case substract:
            operationType = OperationType.add;
            break;
        default:
            throw new RuntimeException("Cannot revert operation: " + operationType);
        }
    }

}

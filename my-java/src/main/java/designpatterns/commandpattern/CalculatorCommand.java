package designpatterns.commandpattern;

public class CalculatorCommand implements Command {

    private OperationType operationType;
    private int value;
    private CalculatorReceiver calculatorReceiver;

    CalculatorCommand(OperationType operationType, int value, CalculatorReceiver calculatorReceiver) {
        this.operationType = operationType;
        this.value = value;
        this.calculatorReceiver = calculatorReceiver;
    }

    @Override
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

    @Override
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

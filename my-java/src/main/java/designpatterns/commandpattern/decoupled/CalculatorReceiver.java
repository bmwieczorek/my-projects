package designpatterns.commandpattern.decoupled;

public class CalculatorReceiver {
    int currentValue = 0;

    public int add(int valueToAdd) {
        currentValue += valueToAdd;
        return currentValue;
    }

    public int substract(int valueToSubstract) {
        currentValue -= valueToSubstract;
        return currentValue;
    }

}
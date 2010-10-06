package designpatterns.commandpattern.decoupled;

import static designpatterns.commandpattern.decoupled.OperationType.add;
import static designpatterns.commandpattern.decoupled.OperationType.substract;
import static java.lang.System.out;

public class Calculating {

    public static void main(String[] args) {
        CalculatorInvoker calculatorInvoker = new CalculatorInvoker();
        CalculatorReceiver calculatorReceiver = new CalculatorReceiver();
        out.println(calculatorInvoker.compute(new CalculatorCommand(add, 5, calculatorReceiver)));// 5
        out.println(calculatorInvoker.compute(new CalculatorCommand(add, 10, calculatorReceiver)));// 15
        out.println(calculatorInvoker.compute(new CalculatorCommand(substract, 1, calculatorReceiver)));// 14
        out.println(calculatorInvoker.undo(2));// 14+1-10=5
        out.println(calculatorInvoker.redo(1));// 5-10=-5
        out.println(calculatorInvoker.compute(new CalculatorCommand(add, 100, calculatorReceiver)));// -5+100=95
    }
}

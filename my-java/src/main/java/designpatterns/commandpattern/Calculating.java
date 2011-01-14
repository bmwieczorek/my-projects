package designpatterns.commandpattern;

public class Calculating {

    public static void main(String[] args) {
        CalculatorInvoker calculatorInvoker = new CalculatorInvoker();
        System.out.println(calculatorInvoker.compute(OperationType.add, 5)); // 5
        System.out.println(calculatorInvoker.compute(OperationType.add, 10)); // 15
        System.out.println(calculatorInvoker.compute(OperationType.substract, 1)); // 14
        System.out.println(calculatorInvoker.undo(2)); // 14+1-10=5
        System.out.println(calculatorInvoker.redo(1)); // 5-10=-5
        System.out.println(calculatorInvoker.compute(OperationType.add, 100)); // -5+100=95
    }

}

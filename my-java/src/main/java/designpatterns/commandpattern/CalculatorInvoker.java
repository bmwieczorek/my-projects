package designpatterns.commandpattern;

import java.util.ArrayList;
import java.util.List;

public class CalculatorInvoker {

	int index = 0;
	List<Command> calculatorCommands = new ArrayList<Command>();
	CalculatorReceiver calculatorReceiver = new CalculatorReceiver();

	public int compute(OperationType operationType, int value) {
		Command calculatorCommand = new CalculatorCommand(
				operationType, value, calculatorReceiver);
		calculatorCommands.add(calculatorCommand);
		index++;
		return calculatorCommand.execute();
	}

	public int redo(int numberOfOperations) {
		if (numberOfOperations > index)
			throw new RuntimeException("Cannot redo more than " + index
					+ " last operations (requested " + numberOfOperations + ")");

		int result = 0;
		for (int i = 1; i <= numberOfOperations; i++) {
			Command calculatorCommand = calculatorCommands.get(index - i);
			calculatorCommands.add(calculatorCommand);
			result = calculatorCommand.execute();
		}
		index += numberOfOperations;
		return result;
	}

	public int undo(int numberOfOperations) {
		if (numberOfOperations > index)
			throw new RuntimeException("Cannot undo more than " + index
					+ " last operations (requested " + numberOfOperations + ")");

		int result = 0;
		for (int i = 1; i <= numberOfOperations; i++) {
			Command command = calculatorCommands.get(index - i);
			calculatorCommands.add(command);
			result = command.unexecute();
		}
		index += numberOfOperations;
		return result;
	}

}

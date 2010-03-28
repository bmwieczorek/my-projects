package designpatterns.commandpattern;

public class CalculatorReceiver {
	int value = 0;

	public int add(int valueToAdd) {
		value += valueToAdd;
		return value;
	}

	public int substract(int valueToSubstract) {
		value -= valueToSubstract;
		return value;
	}

}
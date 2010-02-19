package com.bawi.mywebservices.processor;

import java.util.List;

import com.bawi.services.calculator.Operation;

public class Calculator {

	public int calculate(Operation operation, List<Integer> parameters) {
		switch (operation) {
		case ADD:
			return add(parameters);

		default:
			throw new IllegalArgumentException("Operation: " + operation);
		}
	}

	private static int add(List<Integer> parameters) {
		int result = 0;
		for (int i : parameters) {
			result += i;
		}
		return result;
	}
}

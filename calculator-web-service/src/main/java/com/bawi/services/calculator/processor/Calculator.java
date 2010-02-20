package com.bawi.services.calculator.processor;

import java.util.List;

import org.apache.log4j.Logger;

import com.bawi.services.calculator.Operation;

public class Calculator {
	
	private static final Logger logger = Logger.getLogger(Calculator.class);

	public int calculate(Operation operation, List<Integer> parameters) {
		switch (operation) {
		case ADD:
			return add(parameters);

		default:
			throw new IllegalArgumentException("Invalid operation: " + operation);
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

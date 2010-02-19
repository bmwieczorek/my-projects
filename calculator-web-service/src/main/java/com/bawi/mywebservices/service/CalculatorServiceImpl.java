package com.bawi.mywebservices.service;

import java.util.List;

import com.bawi.mywebservices.processor.Calculator;
import com.bawi.services.calculator.CalculatorRQ;
import com.bawi.services.calculator.CalculatorRS;
import com.bawi.services.calculator.CalculatorServiceInterface;
import com.bawi.services.calculator.Operation;

public class CalculatorServiceImpl implements CalculatorServiceInterface {
	
	private Calculator calculator = new Calculator();
	
	@Override
	public CalculatorRS calculate(CalculatorRQ request) {
		Operation operation = request.getOperation();
		List<Integer> parameters = request.getParameters();
		int result = calculator.calculate(operation, parameters);
		return new CalculatorRS().withResult(result);
	}

}

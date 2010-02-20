package com.bawi.services.service;

import java.util.List;

import com.bawi.services.calculator.CalculatorRQ;
import com.bawi.services.calculator.CalculatorRQEx;
import com.bawi.services.calculator.CalculatorRS;
import com.bawi.services.calculator.CalculatorServiceInterface;
import com.bawi.services.calculator.Operation;
import com.bawi.services.processor.Calculator;

public class CalculatorServiceImpl implements CalculatorServiceInterface {
	
	private Calculator calculator = new Calculator();
	
	
	@Override
	public CalculatorRS calculate(CalculatorRQ request) {
		validate(request);
		Operation operation = request.getOperation();
		List<Integer> parameters = request.getParameters();
		int result = calculator.calculate(operation, parameters);
		return new CalculatorRS().withResult(result);
	}


	private void validate(CalculatorRQ request) {
		((CalculatorRQEx)request).validate();
	}

}

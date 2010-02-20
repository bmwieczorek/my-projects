package com.bawi.services.service;

import java.util.List;

import com.bawi.services.calculator.CalculatorRQ;
import com.bawi.services.calculator.CalculatorRS;
import com.bawi.services.calculator.CalculatorServiceInterface;
import com.bawi.services.calculator.Operation;
import com.bawi.services.calculator.validator.RequestValidator;
import com.bawi.services.processor.Calculator;
import com.bawi.services.service.exception.CalculatorRequestValidator;

public class CalculatorServiceImpl implements CalculatorServiceInterface {
	
	private Calculator calculator = new Calculator();
	
	private RequestValidator validator  = new CalculatorRequestValidator();
	
	@Override
	public CalculatorRS calculate(CalculatorRQ request) {
		
		validator.validate();
		Operation operation = request.getOperation();
		List<Integer> parameters = request.getParameters();
		int result = calculator.calculate(operation, parameters);
		return new CalculatorRS().withResult(result);
	}

}

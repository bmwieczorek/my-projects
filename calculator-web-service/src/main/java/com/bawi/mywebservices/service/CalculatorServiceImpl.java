package com.bawi.mywebservices.service;

import java.util.List;

import com.bawi.mywebservices.processor.Calculator;
import com.bawi.mywebservices.service.exception.CalculatorRequestValidator;
import com.bawi.mywebservices.service.validator.RequestValidator;
import com.bawi.services.calculator.CalculatorRQ;
import com.bawi.services.calculator.CalculatorRS;
import com.bawi.services.calculator.CalculatorServiceInterface;
import com.bawi.services.calculator.Operation;

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

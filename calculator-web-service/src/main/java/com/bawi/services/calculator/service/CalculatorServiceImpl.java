package com.bawi.services.calculator.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.bawi.services.calculator.CalculatorRQ;
import com.bawi.services.calculator.CalculatorRS;
import com.bawi.services.calculator.CalculatorServiceInterface;
import com.bawi.services.calculator.Operation;
import com.bawi.services.calculator.processor.Calculator;
import com.bawi.services.calculator.transformer.JavaXmlTransformer;

public class CalculatorServiceImpl implements CalculatorServiceInterface {

	private static Logger logger = Logger.getLogger(CalculatorServiceImpl.class);
	private Calculator calculator = new Calculator();

	@Override
	public CalculatorRS calculate(CalculatorRQ request) {
		request.validate();
		logger.debug("Request valid");
		Operation operation = request.getOperation();
		List<Integer> parameters = request.getParameters();
		int result = calculator.calculate(operation, parameters);
		return new CalculatorRS().withResult(result);

	}

}

package com.bawi.services.calculator;

import org.springframework.util.Assert;

import com.bawi.services.calculator.exception.InvalidRequestException;

public class CalculatorRQEx extends CalculatorRQ {

	public void validate() throws InvalidRequestException {
		Assert.notNull(operation);
	}

}

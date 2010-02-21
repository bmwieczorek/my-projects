package com.bawi.services.calculator;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

import org.apache.log4j.Logger;

import com.bawi.services.calculator.exception.InvalidRequestException;

public class CalculatorRQ extends CalculatorRQModelBase {

	private static Logger logger = Logger.getLogger(CalculatorRQ.class);

	public void validate() throws InvalidRequestException {
		if (operation == null){
			String message = "Invalid request: operation cannot be null";
			logger.error(message);
			throw new InvalidRequestException(message);
		}
		if (isEmpty(parameters)){
			String message = "Invalid request: parameters cannot be empty " + parameters;
			logger.error(message);
			throw new InvalidRequestException(message);
		}
	}

}
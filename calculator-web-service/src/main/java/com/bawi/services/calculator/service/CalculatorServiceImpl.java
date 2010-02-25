package com.bawi.services.calculator.service;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.bawi.services.calculator.CalculatorFault;
import com.bawi.services.calculator.CalculatorRQ;
import com.bawi.services.calculator.CalculatorRS;
import com.bawi.services.calculator.CalculatorServiceInterface;
import com.bawi.services.calculator.Operation;
import com.bawi.services.calculator.jaxb.JaxbTransformer;
import com.bawi.services.calculator.processor.Calculator;

public class CalculatorServiceImpl implements CalculatorServiceInterface {

	private static Logger logger = Logger
			.getLogger(CalculatorServiceImpl.class);
	private Calculator calculator = new Calculator();

	@Override
	public CalculatorRS calculate(CalculatorRQ request) throws CalculatorFault {
		request.validate();
		String requestXml = transformFromJavaToXml(request);
		logger.debug("Request valid:" + requestXml);
		Operation operation = request.getOperation();
		List<Integer> parameters = request.getParameters();
		int result = calculator.calculate(operation, parameters);
		CalculatorRS response = new CalculatorRS().withResult(result);
		String responseXml = transformFromJavaToXml(response);
		logger.debug("Response valid:" + responseXml);
		return response;
	}

	private String transformFromJavaToXml(Object object) {
		try {
			return JaxbTransformer.toXml(object);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
}

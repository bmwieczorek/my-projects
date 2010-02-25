package com.bawi.services.calculator.service;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.bawi.services.calculator.CalculatorFault;
import com.bawi.services.calculator.CalculatorRQ;
import com.bawi.services.calculator.CalculatorRS;
import com.bawi.services.calculator.CalculatorServiceInterface;
import com.bawi.services.calculator.jaxbtransformer.JaxbTransformer;
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
		int result = calculator.calculate(request.getOperation(), request.getParameters());
		CalculatorRS response = new CalculatorRS().withResult(result);
		String responseXml = transformFromJavaToXml(response);
		logger.debug("Response valid:" + responseXml);
		return response;
	}

	private String transformFromJavaToXml(Object object) {
		try {
			return JaxbTransformer.fromJavaToXml(object);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
}

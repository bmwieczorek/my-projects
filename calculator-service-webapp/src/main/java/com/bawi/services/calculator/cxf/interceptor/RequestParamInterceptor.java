package com.bawi.services.calculator.cxf.interceptor;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.bawi.services.calculator.model.CalculatorRQ;
import com.bawi.services.calculator.model.Operation;

public class RequestParamInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	private Logger log = Logger.getLogger(RequestParamInterceptor.class);

	public RequestParamInterceptor() {
		super(Phase.PRE_LOGICAL);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		List<?> contentElements = message.getContent(List.class);
		for (Object element : contentElements) {
			if (element instanceof CalculatorRQ) {
				CalculatorRQ calculatorRQ = (CalculatorRQ) element;
				Operation operation = calculatorRQ.getOperation();
				MDC.put("operation", operation);
				log.debug("operation" + operation);
			}
		}
	}

}

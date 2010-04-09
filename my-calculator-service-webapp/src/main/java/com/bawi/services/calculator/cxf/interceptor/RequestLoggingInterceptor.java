package com.bawi.services.calculator.cxf.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class RequestLoggingInterceptor extends AbstractPhaseInterceptor<Message> {

	private Logger log = Logger.getLogger(RequestLoggingInterceptor.class);

	public RequestLoggingInterceptor() {
		super(Phase.POST_LOGICAL);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		log.info(MDC.get("request"));
	}

}

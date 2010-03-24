package com.bawi.services.calculator.cxf.interceptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class RequestContentInterceptor extends AbstractPhaseInterceptor<Message> {

	private static final Logger LOG = Logger.getLogger(RequestContentInterceptor.class);

	public RequestContentInterceptor() {
		super(Phase.RECEIVE);
	}

	public void handleMessage(Message message) throws Fault {
		String content = null;
		InputStream is = message.getContent(InputStream.class);
		if (is != null) {
			try {
				byte[] bytes = getBytes(is);
				message.setContent(InputStream.class, new ByteArrayInputStream(bytes));
				content = IOUtils.newStringFromBytes(bytes);
			} catch (IOException e) {
				throw new Fault(e);
			}
		}
		LOG.debug(content);
		MDC.put("request", content);
	}

	private byte[] getBytes(InputStream is) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		IOUtils.copy(is, bos);
		is.close();
		byte[] bytes = bos.toByteArray();
		bos.flush();
		bos.close();
		return bytes;
	}
}

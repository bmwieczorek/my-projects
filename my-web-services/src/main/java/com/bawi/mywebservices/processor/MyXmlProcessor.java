package com.bawi.mywebservices.processor;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.TransformerException;

import com.bawi.myservice.MyComplex;
import com.bawi.myservice.MyEnum;
import com.bawi.myservice.NewOperationRequest;
import com.bawi.myservice.NewOperationResponse;
import com.bawi.mywebservices.NewOperationRequestEx;
import com.bawi.mywebservices.ObjectFactoryEx;

public class MyXmlProcessor {

	private static final String JAXB_PACKAGE = "com.bawi.myservice";

	private static JAXBContext jaxbContext;

	static {
		try {
			jaxbContext = JAXBContext.newInstance(JAXB_PACKAGE);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public String toXml(Object o) throws JAXBException {
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty("jaxb.fragment", Boolean.TRUE);
		marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
		StringWriter writer = new StringWriter();
		marshaller.marshal(o, writer);
		return writer.toString();
	}

	public Object fromXml(String xml) throws JAXBException {
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		unmarshaller.setProperty("com.sun.xml.bind.ObjectFactory",
				new ObjectFactoryEx());
		return unmarshaller.unmarshal(new StringReader(xml));
	}

	@SuppressWarnings("unchecked")
	public <T> T fromXml(String xml, Class<T> clazz) throws JAXBException {
		return (T) fromXml(xml);
	}

	public static void main(String[] args) throws JAXBException, TransformerException {
		MyXmlProcessor myTransformer = new MyXmlProcessor();
		String xml = myTransformer.toXml(createRequest());
		System.out.println(xml);

		NewOperationRequestEx fromXml = (NewOperationRequestEx) myTransformer
				.fromXml(xml);

		System.out.println(fromXml.print());
		

		String xml2 = myTransformer.toXml(createResponse());
		//String xml2 ="<newOperationResponse xmlns=\"http://www.bawi.com/myService/\"><out>AAA</out></newOperationResponse>";
		System.out.println(xml2);

		NewOperationResponse fromXml2 = (NewOperationResponse) myTransformer
				.fromXml(xml2);

		System.out.println(fromXml2.toString());
		


	}

	private static NewOperationRequest createRequest() {
		return new NewOperationRequest().withBooleanIn(true).withIntIn(1)
				.withMyAtt("A").withMyComplex(
						new MyComplex().withRequiredParam(false)).withMyEnum(
						MyEnum.AA).withPatternIn("pp").withStringIn("ss")
				.withUnboundedStrings("x", "y");
	}

	
	private static NewOperationResponse createResponse() {
		return new NewOperationResponse().withOut("AAA");
	}
	
}

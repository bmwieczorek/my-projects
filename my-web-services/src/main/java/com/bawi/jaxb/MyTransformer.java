package com.bawi.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.bawi.myservice.jaxb.MyComplex;
import com.bawi.myservice.jaxb.MyEnum;
import com.bawi.myservice.jaxb.NewOperationRequest;
import com.bawi.myservice.jaxb.NewOperationRequestEx;
import com.bawi.myservice.jaxb.ObjectFactoryEx;

public class MyTransformer {

	private static final String JAXB_PACKAGE = "com.bawi.myservice.jaxb";

	public String toXml(Object o) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext
				.newInstance(JAXB_PACKAGE);
		Marshaller marshaller = jaxbContext.createMarshaller();
		StringWriter writer = new StringWriter();
		marshaller.marshal(o, writer);
		return writer.toString();
	}

	public Object fromXml(String xml) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext
				.newInstance(JAXB_PACKAGE);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		unmarshaller.setProperty("com.sun.xml.bind.ObjectFactory",
				new ObjectFactoryEx());
		return unmarshaller.unmarshal(new StringReader(xml));
	}

	public static void main(String[] args) throws JAXBException,
			TransformerException {
		MyTransformer myTransformer = new MyTransformer();
		String xml = myTransformer.toXml(createRequest());

		String formattedxml = formatXml(xml);

		System.out.println(formattedxml);

		NewOperationRequestEx fromXml = (NewOperationRequestEx) myTransformer
				.fromXml(formattedxml);

		System.out.println(fromXml.print());

	}

	private static String formatXml(String xml)
			throws TransformerFactoryConfigurationError,
			TransformerConfigurationException, TransformerException {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "4");
		StringReader stringReader = new StringReader(xml);
		StringWriter stringWriter = new StringWriter();
		transformer.transform(new StreamSource(stringReader), new StreamResult(
				stringWriter));
		String formattedString = stringWriter.toString().replaceAll("\r\n",
				"\n").trim();
		return formattedString;
	}

	private static Object createRequest() {
		return new NewOperationRequest().withBooleanIn(true).withIntIn(1)
				.withMyAtt("A").withMyComplex(
						new MyComplex().withRequiredParam(false)).withMyEnum(
						MyEnum.AA).withPatternIn("pp").withStringIn("ss")
				.withUnboundedString("x", "y");
	}

}

package com.bawi.services.calculator.jaxbtransformer;

import static com.bawi.services.calculator.model.Operation.ADD;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.bawi.services.calculator.model.CalculatorRQ;
import com.bawi.services.calculator.model.CalculatorRS;

public final class JaxbTransformer {
    private static final String JAXB_PACKAGE = "com.bawi.services.calculator.model";

    private static JAXBContext jaxbContext;

    static {
        try {
            jaxbContext = JAXBContext.newInstance(JAXB_PACKAGE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private JaxbTransformer() {
    }

    public static String fromJavaToXml(Object o) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("jaxb.fragment", Boolean.TRUE);
        marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(o, writer);
        return writer.toString();
    }

    public static Object fromXmlToJava(String xml) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        // unmarshaller.setProperty("com.sun.xml.bind.ObjectFactory",
        // new ObjectFactoryEx());
        return unmarshaller.unmarshal(new StringReader(xml));
    }

    public static void main(String[] args) throws JAXBException {
        marshallAndUnMarshall(createRequest());
        marshallAndUnMarshall(createResponse());
    }

    private static void marshallAndUnMarshall(Object o) throws JAXBException {
        String xml = fromJavaToXml(o);
        System.out.println(xml);
        Object fromXml = fromXmlToJava(xml);
        System.out.println(fromXml);
    }

    private static CalculatorRQ createRequest() {
        return new CalculatorRQ().withParameters(1, 2).withOperation(ADD);
    }

    private static CalculatorRS createResponse() {
        return new CalculatorRS().withResult(123);
    }
}

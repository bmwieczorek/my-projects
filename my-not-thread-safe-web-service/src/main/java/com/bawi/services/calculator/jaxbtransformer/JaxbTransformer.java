package com.bawi.services.calculator.jaxbtransformer;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbTransformer {
    private static final String JAXB_PACKAGE = "com.bawi.services.calculator.model";

    private static JAXBContext jaxbContext;

    static {
        try {
            jaxbContext = JAXBContext.newInstance(JAXB_PACKAGE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static String fromJavaToXml(Object o) {
        Marshaller marshaller;
        try {
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty("jaxb.fragment", Boolean.TRUE);
            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
            StringWriter writer = new StringWriter();
            marshaller.marshal(o, writer);
            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Object fromXmlToJava(String xml) {
        Unmarshaller unmarshaller;
        try {
            unmarshaller = jaxbContext.createUnmarshaller();
            // unmarshaller.setProperty("com.sun.xml.bind.ObjectFactory",
            // new ObjectFactoryEx());
            return unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}

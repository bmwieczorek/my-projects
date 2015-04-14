package com.bawi.jaxb;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.junit.Test;

public class XsdJaxbClassGenerationTest {

    @Test
    public void shouldMarshallToXmlNormal() throws JAXBException {
        com.bawi.jaxb.normal.MyServiceRQ myServiceRQ = new com.bawi.jaxb.normal.MyServiceRQ();
        com.bawi.jaxb.normal.MyElement myElement = new com.bawi.jaxb.normal.MyElement();
        myElement.setMyAttribute("some attribute value");
        myServiceRQ.setMyElement(myElement);
        myServiceRQ.setMyAttribute(Integer.valueOf(123));
        //@formatter:off
        String expectedXml = 
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<MyServiceRQ myAttribute=\"123\">\n" +
                "    <MyElement myAttribute=\"some attribute value\"/>\n" +
                "</MyServiceRQ>\n";
        //@formatter:on

        String generatedXml = marshall(myServiceRQ);

        assertEquals(expectedXml, generatedXml); 
    }
    
    @Test
    public void shouldMarshallToXmlSameAttributeAndElement() throws JAXBException {
        // given
        com.bawi.jaxb.same.attribute.and.element.MyServiceRQ myServiceRQ = new com.bawi.jaxb.same.attribute.and.element.MyServiceRQ();
        com.bawi.jaxb.same.attribute.and.element.MyElement myElement = new com.bawi.jaxb.same.attribute.and.element.MyElement();
        myElement.setMyAttribute("some attribute value");
        myServiceRQ.setMyElement(myElement);
        myServiceRQ.setMyElementAttribute(Integer.valueOf(123));
        //@formatter:off
        String expectedXml = 
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<MyServiceRQ MyElement=\"123\">\n" +
                "    <MyElement MyAttribute=\"some attribute value\"/>\n" +
                "</MyServiceRQ>\n";
        //@formatter:on

        // when
        String generatedXml = marshall(myServiceRQ);

        // then
        assertEquals(expectedXml, generatedXml); 
    }

    @Test
    public void shouldMarshallToXmlSameElementAndComplexType() throws JAXBException {
        // given
        com.bawi.jaxb.same.element.and.complextype.MyServiceRQ myServiceRQ = new com.bawi.jaxb.same.element.and.complextype.MyServiceRQ();
        com.bawi.jaxb.same.element.and.complextype.MyElement myElement = new com.bawi.jaxb.same.element.and.complextype.MyElement();
        myElement.setMyAttribute("some attribute value");
        myServiceRQ.setMyElement(myElement);
        myServiceRQ.setMyAttribute(Integer.valueOf(123));
        //@formatter:off
        String expectedXml = 
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<MyServiceRQ myAttribute=\"123\">\n" +
                "    <MyElement myAttribute=\"some attribute value\"/>\n" +
                "</MyServiceRQ>\n";
        //@formatter:on

        // when
        String generatedXml = marshall(myServiceRQ);

        // then
        assertEquals(expectedXml, generatedXml); 
    }

    private String marshall(Object myServiceRQ) throws JAXBException, PropertyException {
        //JAXBContext jaxbContext = JAXBContext.newInstance("com.bawi.jaxb");
        JAXBContext jaxbContext = JAXBContext.newInstance(myServiceRQ.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(myServiceRQ, stringWriter);
        String myServiceXml = stringWriter.toString();
        return myServiceXml;
    }

}

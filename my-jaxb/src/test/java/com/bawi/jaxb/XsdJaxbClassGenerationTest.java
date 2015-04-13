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
    public void shouldMarshallToXml() throws JAXBException {
        // given
        MyServiceRQ myServiceRQ = new MyServiceRQ();
        MyElement myElement = new MyElement();
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

    private String marshall(MyServiceRQ myServiceRQ) throws JAXBException, PropertyException {
        //JAXBContext jaxbContext = JAXBContext.newInstance(com.bawi.jaxb.MyServiceRQ.class);
        JAXBContext jaxbContext = JAXBContext.newInstance("com.bawi.jaxb");
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(myServiceRQ, stringWriter);
        String myServiceXml = stringWriter.toString();
        return myServiceXml;
    }

}

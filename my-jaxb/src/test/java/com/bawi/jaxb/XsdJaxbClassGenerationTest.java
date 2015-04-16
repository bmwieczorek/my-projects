package com.bawi.jaxb;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.junit.Test;

public class XsdJaxbClassGenerationTest {

    @Test
    public void shouldMarshallToXml() throws JAXBException, JDOMException, IOException {
        com.bawi.jaxb.MyServiceRQ myServiceRQ = new com.bawi.jaxb.MyServiceRQ();
        com.bawi.jaxb.MyElement myElement = new com.bawi.jaxb.MyElement();
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
        
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(new StringReader(generatedXml));

        XPathFactory xPathFactory = XPathFactory.instance();
        XPathExpression<Attribute> xPathExpression = xPathFactory.compile("/MyServiceRQ/@myAttribute", Filters.attribute());
        List<Attribute> evaluate = xPathExpression.evaluate(document.getRootElement());
        Attribute attribute = evaluate.get(0);
        String value = attribute.getValue();
        System.out.println(value);
    }
    
    @Test
    public void shouldMarshallToXmlWhenSameAttributeAndElement() throws JAXBException {
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
    public void shouldMarshallToXmlWhenSameElementAndComplexType() throws JAXBException {
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

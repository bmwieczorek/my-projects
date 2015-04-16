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
import org.jdom2.Element;
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
    }

    @Test
    public void shouldUseXpath() throws JAXBException, JDOMException, IOException {
        //@formatter:off
        String expectedXml = 
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<MyServiceRQ>\n" +
                "    <MyElement myAttribute=\"some attribute value\">some element text value</MyElement>\n" +
                "</MyServiceRQ>\n";
        //@formatter:on

        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(new StringReader(expectedXml));

        XPathFactory xPathFactory = XPathFactory.instance();

        XPathExpression<Attribute> attributeXPathExpression = xPathFactory.compile("/MyServiceRQ/MyElement/@myAttribute", Filters.attribute());
        List<Attribute> attributes = attributeXPathExpression.evaluate(document.getRootElement());
        Attribute attribute = attributes.get(0);
        String attributeValue = attribute.getValue();
        assertEquals("some attribute value", attributeValue);

        XPathExpression<Element> elementXPathExpression = xPathFactory.compile("/MyServiceRQ/MyElement", Filters.element());
        List<Element> elements = elementXPathExpression.evaluate(document.getRootElement());
        Element element = elements.get(0);
        String text = element.getText();
        assertEquals("some element text value", text);
        List<Attribute> attributes2 = element.getAttributes();
        assertEquals("some attribute value", attributes2.get(0).getValue());

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

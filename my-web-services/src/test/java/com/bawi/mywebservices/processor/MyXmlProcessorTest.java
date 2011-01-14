package com.bawi.mywebservices.processor;

import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import com.bawi.myservice.MyComplex;
import com.bawi.myservice.MyEnum;
import com.bawi.myservice.NewOperationRequest;

public class MyXmlProcessorTest {

    private static final String XML_PATH = "src/test/resources/xml/";

    @Test
    public void testToXml() throws IOException, JAXBException {

        String requestXml = IOUtils.toString(new FileReader(XML_PATH + "request.xml"));
        System.out.println(requestXml);
        // or
        // String text2 = IOUtils.toString(new FileInputStream(XML_PATH +
        // "request.xml"));
        // System.out.println(text2);

        MyXmlProcessor myXmlProcessor = new MyXmlProcessor();
        NewOperationRequest unmarshalledRequest = myXmlProcessor.fromXml(requestXml, NewOperationRequest.class);
        System.out.println(unmarshalledRequest);

        NewOperationRequest expectedRequest = new NewOperationRequest().withMyAtt("A").withStringIn("ss")
                .withBooleanIn(true).withIntIn(1).withPatternIn("Z").withMyEnum(MyEnum.AA).withMyList(1, 2, 3)
                .withMyComplex(new MyComplex().withRequiredParam(false)).withUnboundedStrings("x")
                .withUnboundedStrings("y");

        Assert.assertEquals(expectedRequest, unmarshalledRequest);
    }

    @Test
    public void testFromXml() {
        // fail("Not yet implemented");
    }

}

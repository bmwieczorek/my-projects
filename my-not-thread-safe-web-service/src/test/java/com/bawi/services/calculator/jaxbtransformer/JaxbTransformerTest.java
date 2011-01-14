package com.bawi.services.calculator.jaxbtransformer;

import static com.bawi.services.calculator.model.Operation.ADD;
import junit.framework.Assert;

import org.junit.Test;

import com.bawi.services.calculator.model.CalculatorRQ;
import com.bawi.services.calculator.model.CalculatorRS;
import com.bawi.services.calculator.model.CalculatorThreadSafeRQ;

public class JaxbTransformerTest {

    @Test
    public void marshallAndUnMarshall() {

        // given
        CalculatorRQ original = createRequest();
        System.out.println("Original request:\n" + original);

        // when
        String xml = JaxbTransformer.fromJavaToXml(original);
        System.out.println("Marshalled xml:\n" + xml);
        Object marshalledAndUnmarshalled = JaxbTransformer.fromXmlToJava(xml);

        // then
        System.out.println("Marshalled and unmarshalled request:\n" + marshalledAndUnmarshalled);
        Assert.assertEquals(original, marshalledAndUnmarshalled);

    }

    public static void main(String[] args) {
        marshallAndUnMarshall(createResponse());
    }

    private static void marshallAndUnMarshall(Object o) {
        String xml = JaxbTransformer.fromJavaToXml(o);
        System.out.println(xml);
        Object fromXml = JaxbTransformer.fromXmlToJava(xml);
        System.out.println(fromXml);
    }

    private static CalculatorRQ createRequest() {
        return new CalculatorThreadSafeRQ().withParameters(1, 2).withOperation(ADD);
    }

    private static CalculatorRS createResponse() {
        return new CalculatorRS().withResult(123);
    }

}

package com.bawi.camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyCamelSpringRouteTest extends CamelSpringTestSupport {

    //private ExchangeInspector inspector;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteDirectory("target/inbox");
        deleteDirectory("target/outbox");
        // inspector = new ExchangeInspector(context);
    }

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("camel-spring-context.xml");
    }

    @Test
    public void shouldCopyFile() throws Exception {
        // given
        String fileContent = "Hello world";
        String fileName = "hello.txt";
        String endpointUri = "file://target/outbox";
        // inspector.registerSentToEndpointInterceptor(endpointUri);

        // when
        template.sendBodyAndHeader("file://target/inbox", fileContent, Exchange.FILE_NAME, fileName);

        Thread.sleep(1000);

        // then
        File destFile = new File("target/outbox/" + fileName);
        assertTrue("Expected to copy file", destFile.exists());
        assertEquals(fileContent, context.getTypeConverter().convertTo(String.class, destFile));
        // assertTrue(exchange.getProperty(MyLoggingProcessor.MY_PROCESSED_PROPERTY, boolean.class));

    }
}

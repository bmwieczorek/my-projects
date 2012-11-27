package com.bawi.camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bawi.camel.processor.MyLoggingProcessor;

public class MyCamelSpringRouteTest extends CamelSpringTestSupport {

    private ExchangeInspector inspector;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteDirectory("target/inbox");
        deleteDirectory("target/outbox");
        inspector = new ExchangeInspector(context);
    }

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("camel-spring-context.xml");
    }

    @Ignore
    @Test
    public void shouldCopyFile() throws Exception {
        // given
        String fileContent = "Hello world";
        String fileName = "hello.txt";
        String endpointUri = "file://target/outbox";
        inspector.registerSentToEndpointInterceptor(endpointUri);
        Exchange exchange = inspector.getExchange();
        exchange.getOut().setBody(fileContent);

        // when
        template.sendBodyAndHeader("file://target/inbox", fileContent, Exchange.FILE_NAME, fileName);
        Thread.sleep(1000);

        // then
        File file = new File("target/outbox/" + fileName);
        assertTrue("File not copied", file.exists());
        assertEquals(fileContent, context.getTypeConverter().convertTo(String.class, file));
        assertTrue(exchange.getProperty(MyLoggingProcessor.MY_PROCESSED_PROPERTY, boolean.class));

    }
}

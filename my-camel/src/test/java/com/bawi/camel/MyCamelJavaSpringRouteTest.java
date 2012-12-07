package com.bawi.camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyCamelJavaSpringRouteTest extends CamelSpringTestSupport {

    @Override
    public void setUp() throws Exception {
        deleteDirectory("target/inbox");
        deleteDirectory("target/outbox");
        super.setUp();
    }

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("camel-context.xml");
    }

    @Test
    public void shouldCopyFile() throws InterruptedException {
        // given
        String fileContent = "Hello world";
        String fileName = "hello.txt";

        // when
        template.sendBodyAndHeader("file://target/inbox", fileContent, Exchange.FILE_NAME, fileName);
        Thread.sleep(1000);

        // then
        File destFile = new File("target/outbox/" + fileName);
        assertTrue("Expected to copy file", destFile.exists());
        assertEquals(fileContent, context.getTypeConverter().convertTo(String.class, destFile));
    }

}

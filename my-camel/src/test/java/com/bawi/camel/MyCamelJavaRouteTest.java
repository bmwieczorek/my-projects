package com.bawi.camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class MyCamelJavaRouteTest extends CamelTestSupport {

    @Override
    public void setUp() throws Exception {
        deleteDirectory("target/inbox");
        deleteDirectory("target/outbox");
        super.setUp();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("file://target/inbox").to("file://target/outbox");
            }
        };
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

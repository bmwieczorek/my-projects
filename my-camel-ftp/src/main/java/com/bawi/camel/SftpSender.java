package com.bawi.camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

class SftpSender {

    public static void main(String[] args) throws Exception {
        DefaultCamelContext defaultCamelContext = new DefaultCamelContext();
        ProducerTemplate producerTemplate = defaultCamelContext.createProducerTemplate();
        File file = new File("pom.xml");
        producerTemplate.sendBodyAndHeader("sftp://user@host/?password=pass&passiveMode=false", file, Exchange.FILE_NAME, file.getName());
        producerTemplate.stop();
    }
}

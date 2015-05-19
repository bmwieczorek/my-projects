package com.bawi.camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class StringContextSftpSender {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("my-camel-sftp-spring.xml");
        ProducerTemplate producerTemplate = applicationContext.getBean(ProducerTemplate.class);
        File file = new File("pom.xml");
        String endpointUriTemplate = "%s://%s@%s/%s?password=%s&passiveMode=%s";
        String endpointUri = String.format(endpointUriTemplate, "sftp", "user", "host", "fileName", "password", "false");
        producerTemplate.sendBodyAndHeader(endpointUri, file, Exchange.FILE_NAME,
                file.getName());
        applicationContext.close();
    }

}
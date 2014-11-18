package com.bawi.camel;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyCamelSpringStandalone {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("my-camel-spring.xml");
        Thread.sleep(5000); // Work for 5 secs and stops the route
        applicationContext.close();
    }

}

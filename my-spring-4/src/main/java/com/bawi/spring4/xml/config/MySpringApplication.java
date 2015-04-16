package com.bawi.spring4.xml.config;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySpringApplication {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:/xml.config/my-service-context.xml");
        MyService myService = context.getBean(MyService.class);
        myService.greet("Mike");
        context.close();
    }

}

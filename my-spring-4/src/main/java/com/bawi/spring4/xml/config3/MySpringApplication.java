package com.bawi.spring4.xml.config3;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bawi.spring4.xml.config3.component.MyService;

public class MySpringApplication {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:/xml.config3/my-service-context.xml");
        MyService myService = context.getBean(MyService.class);
        myService.greet("Mike");
        context.close();
    }

}

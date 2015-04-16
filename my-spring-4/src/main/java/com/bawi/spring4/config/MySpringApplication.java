package com.bawi.spring4.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

@Configuration
@ComponentScan
public class MySpringApplication {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MySpringApplication.class);
        MyService myService = context.getBean(MyService.class);
        myService.greet("Mike");
        context.close();
    }

}

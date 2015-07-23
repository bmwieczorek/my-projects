package com.bawi.spring4.annotation.config2.component.scan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

import com.bawi.spring4.annotation.config2.component.MyService;

@Configuration
@ComponentScan(basePackages = "com.bawi.spring4.annotation.config2.component")
public class MySpringApplication {

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MySpringApplication.class);
        MyService myService = context.getBean(MyService.class);
        myService.greet("Mike");
        context.close();
    }

}

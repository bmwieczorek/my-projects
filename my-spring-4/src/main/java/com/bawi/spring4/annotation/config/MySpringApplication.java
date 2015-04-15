package com.bawi.spring4.annotation.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

@Configuration
public class MySpringApplication {

    @Bean
    public Greeting englishGreeting() {
        return new EnglishGreeting();
    }

    @Bean
    public Greeting germanGreeting() {
        return new GermanGreeting();
    }

    @Bean
    public MyService myService() {
        return new MyService(englishGreeting());
    }

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MySpringApplication.class);
        MyService myService = context.getBean(MyService.class);
        myService.greet("Mike");
        context.close();
    }
}

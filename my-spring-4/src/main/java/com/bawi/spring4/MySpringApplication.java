package com.bawi.spring4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

@Configuration
public class MySpringApplication {

    public static class MyService {
        public void greet(String name) {
            System.out.println("Hello " + name + "!");
        }
    }

    @Bean
    public MyService myService() {
        return new MyService();
    }

    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MySpringApplication.class);
        MyService myService = context.getBean(MyService.class);
        myService.greet("Mike");
        context.close();
    }
}

package com.bawi.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.Main;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyCamelSpringMT {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("my-camel-spring-mt.xml");
        main.setApplicationContext(applicationContext);
        main.enableHangupSupport();
        CamelContext camelContext = applicationContext.getBean(CamelContext.class);
        MyRouteBuilder myRouteBuilder = applicationContext.getBean(MyRouteBuilder.class);
        camelContext.addRoutes(myRouteBuilder);
        camelContext.startRoute("myRoute");
        System.out.println("aaa");
        //main.run();
    }

}

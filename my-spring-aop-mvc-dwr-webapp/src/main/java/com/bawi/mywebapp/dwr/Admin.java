package com.bawi.mywebapp.dwr;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Admin {

    private static final Log LOGGER = LogFactory.getLog(Admin.class);

    /**
     * I defined a method welcomeUser, I make a dwr call to invoke that method,
     * I want to advice (AOP) thiss method, thiss method cannot me defined in
     * the classs that dwr uses b
     */

    public String greatAdmin(String username) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("../mywebapp-servlet.xml");
        Server server = (Server) ctx.getBean("server");
        return server.welcomeUser(username);
    }

    public String greetPerson(String person) {
        Date date = new Date();
        LOGGER.info("ServerImpl: I am in the method greetPerson");
        return "Greeted " + person + " " + date.toString();

    }
}
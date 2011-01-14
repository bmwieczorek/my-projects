package com.bawi.mywebapp.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logging {

    private static final Log LOGGER = LogFactory.getLog(Logging.class);

    public Logging() {
    }

    @Pointcut("execution(* *.welcomeUser(..))")
    // @Pointcut("execution(* *.greetPerson(..))")
    public void welcome() {
    }

    // @Before("welcome()") //or
    @Before("execution(* *.welcomeUser(..))")
    public void logUserVisit() {
        LOGGER.info("Audit logging: before execution method welcomeUser");
    }

}

package com.bawi.quartz.scheduler;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySpringQuartzTest {

   public static class MyTask {

        private static final Logger LOGGER = LoggerFactory.getLogger(MyTask.class);

        public void logExecution() {
            LOGGER.info("Executing ...");
        }
    };

    @Test
    public void shouldLog() throws Exception { 
        AutoCloseable context = new ClassPathXmlApplicationContext("classpath:/spring-quartz.xml");
        TimeUnit.SECONDS.sleep(5);
        context.close();
    }
}

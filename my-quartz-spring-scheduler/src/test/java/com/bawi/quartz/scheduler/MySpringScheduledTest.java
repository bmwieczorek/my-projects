package com.bawi.quartz.scheduler;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

public class MySpringScheduledTest {

    public static class MyTask {
        private static final Logger LOGGER = LoggerFactory.getLogger(MyTask.class);

        // schedule job every 1 second
        //@Scheduled(fixedRate = 1000)
        @Scheduled(cron = "*/1 * * * * ?")
        public void logExecution() {
            LOGGER.info("Executing ...");
        }
    }
    
    @Test
    public void shouldLog() throws Exception { 
        AutoCloseable context = new ClassPathXmlApplicationContext("classpath:spring-scheduler.xml");
        TimeUnit.SECONDS.sleep(5);
        context.close();
    }
    
}

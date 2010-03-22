package com.bawi.myjmxscheduler.scheduler;

import java.util.Date;

import org.apache.log4j.Logger;

public class PrintServiceImpl {
    private static Logger log = Logger.getLogger(PrintServiceImpl.class);
    
    public void execute(){
        log.info("bawi " + new Date());
    }
}

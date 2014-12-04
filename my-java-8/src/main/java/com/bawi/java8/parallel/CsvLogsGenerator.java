package com.bawi.java8.parallel;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvLogsGenerator {
    
    private static Logger LOGGER = LoggerFactory.getLogger(CsvLogsGenerator.class);
    
    public static void main(String[] args) throws InterruptedException {
        //for (int j = 0; j < 29999999; j++) { //     <param name="MaxFileSize" value="500MB" /> no sleep, creates 3GB (6 x 0.5GB): feedback: can read 3GB despite 1GB memory, duration: 
        //for (int j = 0; j < 9999999; j++) { //     <param name="MaxFileSize" value="500MB" /> no sleep, creates 1GB (2 x 0.5GB), feedback: duration: 
        for (int j = 0; j < 200; j++) { //             <param name="MaxFileSize" value="1MB" /> 1 s sleep, creates , feedback: tps calculation
            LOGGER.debug("migration-edifacts-14150248704395444.txt,0,{},2014-08-15,EY,3SL2DF", String.format("607456%07d", duplicateEvenNumber(j)));
            TimeUnit.SECONDS.sleep(1); // 1 per second - test tps
        }
    }

    private static int duplicateEvenNumber(int j) {
        return j-(j%2);
    }

}

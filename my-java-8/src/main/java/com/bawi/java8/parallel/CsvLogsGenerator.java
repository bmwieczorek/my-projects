package com.bawi.java8.parallel;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvLogsGenerator {

    private static Logger LOGGER = LoggerFactory.getLogger(CsvLogsGenerator.class);

    public static void main(String[] args) throws InterruptedException {
        //for (int j = 0; j < 649; j++) {      // MaxFileSize 10KB  no sleep, creates 7 files up to 100 lines:  feedback: show fork join process - see target (analysis)
        for (int j = 0; j < 29999999; j++) { // MaxFileSize 500MB no sleep, creates 3GB (6 x 0.5GB):          feedback: can read 3GB despite 2GB memory, duration: 127s 
        //for (int j = 0; j < 9999999; j++) {  // MaxFileSize 500MB no sleep, creates 1GB (2 x 0.5GB),          feedback: duration: 33s
        //for (int j = 0; j < 250; j++) {        // MaxFileSize 10KB  1s sleep, creates ,                         feedback: show stats 1 tps calculation
            LOGGER.debug("migration-edifacts-14150248704395444.txt,0,{},2014-08-15,EY,3SL2DF", String.format("607456%07d", duplicateEvenNumber(j)));
//            TimeUnit.SECONDS.sleep(1); // 1 per second - test tps
        }
    }

    private static int duplicateEvenNumber(int j) {
        return j-(j%2);
    }

}

package com.bawi.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvLogsGenerator {
    
    private static Logger LOGGER = LoggerFactory.getLogger(CsvLogsGenerator.class);
    
    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 29999999; j++) { //     <param name="MaxFileSize" value="500MB" /> no sleep, creates 3GB (6 x 0.5GB): feedback: can read 3GB despite 1GB memory
            LOGGER.debug("migration-edifacts-14150248704395444.txt,0,{},2014-08-15,EY,3SL2DF", String.format("607456%07d", duplicateEvenNumber(j)));
        }
    }

    private static int duplicateEvenNumber(int j) {
        return j-(j%2);
    }

}

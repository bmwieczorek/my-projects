package com.bawi;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

public class TestSyslog {

    private static final Logger logger = Logger.getLogger("syslog.monitoring");

    @Test
    public void shouldLogToSyslogForLinux() throws Exception {
        // given
        String message = "My message!!!";

        // when
        logger.error(message);

        // then
        if (isLinux()) {
            assertTrue(fileContainsMessage("/var/log/syslog", message));
            assertTrue(fileContainsMessage("/var/log/user.log", message));
        }
    }

    private boolean isLinux() {
        String os = System.getProperty("os.name");
        return "Linux".equals(os);
    }

    private boolean fileContainsMessage(String userLogFile, String message) throws IOException {
        String fileAsString = FileUtils.readFileToString(new File(userLogFile));
        return fileAsString.contains(message);
    }
}

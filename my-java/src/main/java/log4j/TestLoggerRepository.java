package log4j;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

public class TestLoggerRepository {

    @Test
    public void should() {
        // given
        String name = "NonExistingLoggerName";

        // when
        Logger exists = LogManager.exists(name);

        // then
        assertNull(exists);
    }

    @Test
    public void shouldCreateLoggerInRepository() {
        // given
        String loggerName = "xxx";

        // when
        LogManager.getLogger(loggerName);

        // then
        assertNotNull(LogManager.exists(loggerName));

    }

    @Test
    public void shouldCreateLoggerInRepository2() {
        // given
        String loggerName = "yyy";

        // when
        Logger.getLogger(loggerName);

        // then
        assertNotNull(LogManager.exists(loggerName));

    }

    @Test
    public void shouldReturnCachedLogger() {
        // given
        String loggerName = "zzz";

        // when
        Logger logger1 = Logger.getLogger(loggerName);

        // then
        Logger logger2 = Logger.getLogger(loggerName);
        assertEquals(logger1, logger2);
    }

    @Test
    public void shouldReturnCachedLoggerForDifferentThread() throws InterruptedException {
        // given
        final String loggerName = "zzz";

        // when
        Logger logger = Logger.getLogger(loggerName);

        // then
        final Logger[] loggers = new Logger[1];
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                loggers[0] = Logger.getLogger(loggerName);
            }
        });
        t.start();
        sleep(10);
        assertEquals(logger, loggers[0]);
    }
}

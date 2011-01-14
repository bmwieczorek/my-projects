package logging;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.AsyncAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class SyncVsAsyncLogger {

    static Map<String, Logger> carrierLoggers = new HashMap<String, Logger>();

    // private static final Logger logger =
    // Logger.getLogger(SyncVsAsyncLogger.class);

    private static final String DATE_PATTERN = "'.'yyyy-MM-dd";
    private static final String PATTERN_LAYOUT = "%5p (%d{DATE}) [%t] - %m%n";

    public static void main(String[] args) {
        // Logger exists = LogManager.exists("SS.com.bawi");

        long start = System.currentTimeMillis();
        // Logger logger = Logger.getLogger("K0.com.bawi.static");
        for (int i = 0; i < 500; i++) {
            logFromLog4jXml("K0");
            logFromLog4jXml("AT");
            // logDynamically("K0");
            // logDynamically("AT");
            // Thread.sleep(10);
        }
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
    }

    private static String createMessage() {
        char[] bigstring = new char[30 * 1024];
        Arrays.fill(bigstring, 'a');
        return new String(bigstring);
    }

    private static void logFromLog4jXml(String carrier) {
        Logger logger = Logger.getLogger(carrier + ".com.bawi.static");
        logger.error(createMessage());
    }

    @SuppressWarnings("unused")
    private static void logDynamically(String carrier) throws IOException, InterruptedException {
        Logger logger;
        if (carrierLoggers.containsKey(carrier)) {
            logger = carrierLoggers.get(carrier);
        } else {
            logger = createAsyncLogger(carrier);
            carrierLoggers.put(carrier, logger);
        }
        // logger = createAsyncLogger(carrier);
        logger.error(createMessage());
    }

    private static Logger createAsyncLogger(String carrier) throws IOException {
        Logger logger = Logger.getLogger(carrier + ".com.bawi.dynamic");
        logger.setAdditivity(false);
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender(new PatternLayout(
                PATTERN_LAYOUT), carrier + "-carrier.log", DATE_PATTERN);
        AsyncAppender asyncAppender = new AsyncAppender();
        // asyncAppender.setBufferSize(Integer.MAX_VALUE);
        asyncAppender.setBufferSize(500000);
        asyncAppender.addAppender(dailyRollingFileAppender);
        // logger.addAppender(dailyRollingFileAppender);
        logger.addAppender(asyncAppender);
        return logger;
    }

}

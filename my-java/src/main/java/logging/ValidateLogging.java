package logging;

import org.apache.log4j.Logger;

public class ValidateLogging {

    private static final Logger logger = Logger.getLogger(ValidateLogging.class);

    public static void main(String[] args) {
        // Logger l = logger;
        logger.debug("Bla");
    }
}

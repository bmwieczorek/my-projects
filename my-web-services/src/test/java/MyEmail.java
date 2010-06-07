import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

public class MyEmail {

    // public static void main(String[] args) throws IOException {
    // for (int i = 0; i < 3; i++) {
    // Logger logger = getLogger("K" + i);
    // RollingFileAppender appender = (RollingFileAppender) logger
    // .getAppender("R");
    // logger.addAppender(new RollingFileAppender(
    // appender.getLayout(), "K" + i + ".log", true));
    // System.out.println("K" + i + logger.getLevel());
    // logger.error("K" + i + " logging ...");
    //
    // }
    //
    // }

    static Logger myEmailLogger = Logger.getLogger(MyEmail.class);

    public static void main(String[] args) {
        NDC.push("Client #1");
        // MDC.put("incident", "Client #1");
        myEmailLogger.error("aaa");
        NDC.pop();
        System.out.println(myEmailLogger.getName());
        System.out.println("a\nb");
    }
}

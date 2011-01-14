import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

public final class MyEmail {

    private static final Logger MY_EMAIL_LOGGER = Logger.getLogger(MyEmail.class);

    private MyEmail() {
    }

    public static void main(String[] args) {
        NDC.push("Client #1");
        // MDC.put("incident", "Client #1");
        MY_EMAIL_LOGGER.error("aaa");
        NDC.pop();
        System.out.println(MY_EMAIL_LOGGER.getName());
        System.out.println("a\nb");
    }
}

package junit.spring.testing;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextSingleton {

    private static final ClassPathXmlApplicationContext CTX = new ClassPathXmlApplicationContext(
            "/junit/spring/testing/my-bean-context.xml");

    private ContextSingleton() {
    }

    public static ClassPathXmlApplicationContext getInstance() {
        return CTX;
    }

}

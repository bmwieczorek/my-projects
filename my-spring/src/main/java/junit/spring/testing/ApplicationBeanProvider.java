package junit.spring.testing;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationBeanProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationBeanProvider.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> type) {
        Map<?, T> beansOfType = applicationContext.getBeansOfType(type);
        for (T bean : beansOfType.values()) {
            return bean;
        }
        throw new RuntimeException("Bean of type " + type + " not found");
    }

    public static void startNewContext() {
        new ClassPathXmlApplicationContext("/junit/spring/testing/my-bean-context.xml");
        ContextSingleton.getInstance();
    }

    public static void startContext() {
        ContextSingleton.getInstance();
    }

}

package com.bawi.single.instantiation;

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

    public static void startContext() {
        new ClassPathXmlApplicationContext("/com/bawi/single/instantiation/my-bean-context.xml");
    }

}

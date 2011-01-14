package com.bawi.myspringapp.context;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationBeanProvider implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
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

}

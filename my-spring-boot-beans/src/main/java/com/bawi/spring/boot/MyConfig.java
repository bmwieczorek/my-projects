package com.bawi.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties // needed for application.properties
@Configuration // needed for @Bean
public class MyConfig {

    private String appName;

    @Bean(initMethod = "init", destroyMethod = "destroy")
    MyBean myBean() {
        return new MyBean(appName);
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}

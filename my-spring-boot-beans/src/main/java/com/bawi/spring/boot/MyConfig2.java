package com.bawi.spring.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties // needed for application.properties
@Configuration // needed for @Bean
public class MyConfig2 {

    @Value("${appName2}") // despite supported by Spring, Intellij does not scroll from property to class member
            String appName2;

    @Bean(initMethod = "init", destroyMethod = "destroy")
    MyBean myBean2() {
        return new MyBean(appName2);
    }

}

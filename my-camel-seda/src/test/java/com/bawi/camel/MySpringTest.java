package com.bawi.camel;

import org.junit.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class MySpringTest {
    
    @Ignore
    @Test
    public void shouldXmlClassPathCtxSeeProgramaticallyCreatedBean() throws Exception {

        // given
        int maximumRequestCount = 10;
        InputParameters inputParameters = new InputParameters(maximumRequestCount);
        DefaultListableBeanFactory parentBeanFactory = new DefaultListableBeanFactory();
        parentBeanFactory.registerSingleton("inputParameters", inputParameters);
        GenericApplicationContext parentContext = new GenericApplicationContext(parentBeanFactory);
        parentContext.refresh(); // needed to start the GenericApplicationContext

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"my-spring.xml"}, parentContext);
        MySedaRouteBuilder2 builder = context.getBean(MySedaRouteBuilder2.class);
        
        // then
        Assert.assertEquals(maximumRequestCount, builder.getInputParameters().getMaximumRequestCount());
        context.close();
    }

}

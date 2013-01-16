package com.bawi.camel.timeout.processor;

import java.util.Collection;
import java.util.Map;

import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SampleRouteTest extends CamelSpringTestSupport {

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("/camel-context.xml");
    }


    
    
    @Test
    public void should() { 
        // given
        BusinessObject businessObject = new BusinessObject();
        BusinessObjectCreatorStub businessObjectCreatorStub = getBeanByType(BusinessObjectCreatorStub.class);
        businessObjectCreatorStub.setBusinessObject(businessObject);
        
        DBDao dbDao = getBeanByType(DBDao.class);

        // when
        template.sendBody(SampleRoute.FROM_URI, "someText");

        // then
        Mockito.verify(dbDao, Mockito.times(1)).insert(businessObject);
        

    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private <T> T getBeanByType(Class<T> clazz){
        Map beansOfType = applicationContext.getBeansOfType(clazz);
        int size = beansOfType.size();
        if (size != 1){
            throw new RuntimeException("Expected to find exactly one bean matching type: " + clazz + ", found: " + size);
        }
        Collection values = beansOfType.values();
        Object next = values.iterator().next();
        return (T)next;
    }
    
}

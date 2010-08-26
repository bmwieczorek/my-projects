package com.bawi.myspringapp;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyServiceTest {

    @Test
    public void shouldReturnTheSameCacheProvider() throws Exception {
        new ClassPathXmlApplicationContext("application-context.xml");
        MyService myService1 = new MyService();
        MyService myService2 = new MyService();

        assertNotSame(myService1, myService2);

        // when

        CacheProvider cacheProvider1 = myService1.getCacheProvider();
        CacheProvider cacheProvider2 = myService2.getCacheProvider();

        // then
        assertEquals(cacheProvider1, cacheProvider2);
    }

}

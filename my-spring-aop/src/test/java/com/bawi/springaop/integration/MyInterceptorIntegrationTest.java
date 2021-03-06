package com.bawi.springaop.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bawi.springaop.service.MyServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/aop-context.xml" })
public class MyInterceptorIntegrationTest {

    @Autowired
    private MyServiceImpl myServiceImpl;

    @Test
    public void shouldInteceptInterfacedMethod() {
        // when
        myServiceImpl.myMethod();
    }

    @Test
    public void shouldNotInteceptNonInterfacedMyMethod() {

        // need cglib to autoproxy
        // when
        myServiceImpl.myNonInterfacedMethod();
    }
}

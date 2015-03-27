package com.sabre.ticketing.cobertura.hello;

import junit.framework.Assert;

import org.junit.Test;

public class SayHelloTest {

    @Test
    public void testSayHello() {
        Assert.assertEquals("?", new SayHello().sayHello(null));
    }

}

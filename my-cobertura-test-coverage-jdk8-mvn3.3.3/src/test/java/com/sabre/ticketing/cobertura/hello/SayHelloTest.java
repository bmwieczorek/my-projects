package com.sabre.ticketing.cobertura.hello;

import org.junit.Assert;
import org.junit.Test;

public class SayHelloTest {

    @Test
    public void testSayHello() {
        Assert.assertEquals("?", new SayHello().sayHello(null));
    }

}

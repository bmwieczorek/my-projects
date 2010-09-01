package com.bawi.single.instantiation;

import static com.bawi.single.instantiation.ApplicationBeanProvider.getBean;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

// public class MyDerivedClass2Test extends MyAbstractTestBaseClass {
public class MyDerivedClass2Test extends MyAbstractTestBaseClass {

    @Test
    public void shouldSayHello() {
        // given

        // when
        MyBean myBean = getBean(MyBean.class);

        // then
        assertEquals("Hello", myBean.sayHello());
    }

    @Test
    public void shouldSayGoodbye() {
        // given

        // then
        assertEquals("Goodbye", getBean(MyBean.class).sayGoodbye());
    }

}

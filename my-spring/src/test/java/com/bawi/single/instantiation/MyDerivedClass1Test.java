package com.bawi.single.instantiation;

import static com.bawi.single.instantiation.ApplicationBeanProvider.getBean;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyDerivedClass1Test extends MyAbstractTestBaseClass {
    // public class MyDerivedClass1Test {

    // initializes context and creates beans for every test case
    // @Before
    // public void init() {
    // ApplicationBeanProvider.startCarrierLoggingContext();
    // }

    // initializes context and creates beans only once, common for test cases
    // @BeforeClass
    // public static void init() {
    // ApplicationBeanProvider.startCarrierLoggingContext();
    // }

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

        // when
        MyBean myBean = getBean(MyBean.class);

        // then
        assertEquals("Goodbye", myBean.sayGoodbye());
    }
}

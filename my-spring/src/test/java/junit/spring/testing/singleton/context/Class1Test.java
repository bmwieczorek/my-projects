package junit.spring.testing.singleton.context;

import static org.junit.Assert.assertEquals;
import junit.spring.testing.ApplicationBeanProvider;
import junit.spring.testing.MyBean;

import org.junit.Before;
import org.junit.Test;

public class Class1Test {

    // initializes context only once globally
    // here once in this class, once for all test cases for all classes
    @Before
    public void init() {
        ApplicationBeanProvider.startContext();
    }

    @Test
    public void shouldSayHello() {
        // when
        MyBean myBean = ApplicationBeanProvider.getBean(MyBean.class);

        // then
        assertEquals("Hello", myBean.sayHello());
    }

    @Test
    public void shouldSayGoodbye() {
        // when
        MyBean myBean = ApplicationBeanProvider.getBean(MyBean.class);

        // then
        assertEquals("Goodbye", myBean.sayGoodbye());
    }
}

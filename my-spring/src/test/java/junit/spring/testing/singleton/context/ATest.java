package junit.spring.testing.singleton.context;

import static org.junit.Assert.assertEquals;
import junit.spring.testing.ApplicationBeanProvider;
import junit.spring.testing.MyBean;

import org.junit.Test;

public class ATest {

    // initializes context only once globally
    // here once in this class, once for all test cases for all classes

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

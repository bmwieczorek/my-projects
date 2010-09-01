package junit.spring.testing.annotation.before;

import static org.junit.Assert.assertEquals;
import junit.spring.testing.ApplicationBeanProvider;
import junit.spring.testing.MyBean;

import org.junit.Before;
import org.junit.Test;


public class Class2Test {

    // initializes context and creates beans for every test case
    // here twice in this class, 4 times for two classes with 2 test cases each
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

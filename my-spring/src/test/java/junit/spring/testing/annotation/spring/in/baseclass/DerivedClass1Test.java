package junit.spring.testing.annotation.spring.in.baseclass;

import static org.junit.Assert.assertEquals;
import junit.spring.testing.ApplicationBeanProvider;
import junit.spring.testing.MyBean;

import org.junit.Test;

public class DerivedClass1Test extends AbstractTestBase {

    // by annotating base class with spring junit runner and specifying
    // configuration context files
    // initialize context only once for two classes, each containing 2 test
    // cases (total 4 test cases)

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

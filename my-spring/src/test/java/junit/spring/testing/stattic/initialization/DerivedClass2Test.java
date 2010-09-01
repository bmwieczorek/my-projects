package junit.spring.testing.stattic.initialization;

import static org.junit.Assert.assertEquals;
import junit.spring.testing.ApplicationBeanProvider;
import junit.spring.testing.MyBean;

import org.junit.Test;

public class DerivedClass2Test extends AbstractTestBase {

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

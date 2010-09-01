package junit.spring.testing.annotation.spring.in.classes;

import static org.junit.Assert.assertEquals;
import junit.spring.testing.ApplicationBeanProvider;
import junit.spring.testing.MyBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/junit/spring/testing/my-bean-context.xml" })
public class Class2Test {

    // when two classes are run - initialized only once globally
    // when this class is run alone - initialized only once - common for all test cases

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

package junit.spring.testing.annotation.beforeclass.in.classes;

import static org.junit.Assert.assertEquals;
import junit.spring.testing.ApplicationBeanProvider;
import junit.spring.testing.MyBean;

import org.junit.BeforeClass;
import org.junit.Test;

public class Class2Test {

    // initializes context and creates beans only once for a class, common for
    // test cases in a class
    // here only once in this class, for two classes - twice
    @BeforeClass
    public static void init() {
        ApplicationBeanProvider.startNewContext();
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

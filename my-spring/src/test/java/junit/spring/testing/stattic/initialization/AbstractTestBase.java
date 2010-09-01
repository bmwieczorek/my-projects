package junit.spring.testing.stattic.initialization;

import junit.spring.testing.ApplicationBeanProvider;

public abstract class AbstractTestBase {

    // static block is executed before spring context configuration for annotated class

    // initialized only once for all the classes and test cases (totally once)
    static {
        // getBean(MyBean.class).sayGoodbye();
        ApplicationBeanProvider.startContext();
    }

}

package junit.spring.testing.annotation.beforeclass.in.baseclass;

import junit.spring.testing.ApplicationBeanProvider;

import org.junit.BeforeClass;

public abstract class AbstractTestBase {

    // initializes context and creates beans only once for a derived class, common for test cases in a derived class
    // for two derived classes - initializes twice in total
    @BeforeClass
    public static void init() {
        ApplicationBeanProvider.startNewContext();
    }

}

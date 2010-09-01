package com.bawi.single.instantiation;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/com/bawi/single/instantiation/my-bean-context.xml" })
public abstract class MyAbstractTestBaseClass {

    // gets executed before spring context configuration for annotated class
    static {
        // getBean(MyBean.class).sayGoodbye();
        ApplicationBeanProvider.startContext();
    }

}

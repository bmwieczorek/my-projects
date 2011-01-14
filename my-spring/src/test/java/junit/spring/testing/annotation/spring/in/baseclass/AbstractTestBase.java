package junit.spring.testing.annotation.spring.in.baseclass;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/junit/spring/testing/my-bean-context.xml" })
public abstract class AbstractTestBase {

    // by annotating base class with spring junit runner and specifying
    // configuration context files
    // initialize context only once for two classes, each containing 2 test
    // cases (total 4 test cases)

    // not class is abstract so that junit does not try to run it
}

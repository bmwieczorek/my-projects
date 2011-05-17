package concurency;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InheritableThreadLocalTest {

    private ThreadLocal<String> nonInheritableTL = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "one";
        };
    };

    private ThreadLocal<String> inheritableTL = new InheritableThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "two";
        };
    };

    @Test
    public void shouldChildThreadInheritDataFromParent() {
        // given
        assertEquals("one", nonInheritableTL.get());
        assertEquals("two", inheritableTL.get());

        // when
        nonInheritableTL.set("three");
        inheritableTL.set("four");
        new Thread(new Runnable() {

            @Override
            public void run() {
                assertEquals("one", nonInheritableTL.get());
                assertEquals("four", inheritableTL.get());
            }
        }

        ).start();

        // then

    }
}

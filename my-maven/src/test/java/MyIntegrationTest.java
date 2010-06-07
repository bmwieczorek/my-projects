import org.junit.Test;

public class MyIntegrationTest {

    @Test
    public void test() {
        System.out.println(MySingleton.INSTANCE.i);
    }
}

import org.junit.Test;

public class MyJunit2Test {

    @Test
    public void test() {
        System.out.println(MySingleton.INSTANCE.getI());
    }
}

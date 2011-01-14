import org.junit.Test;

public class MyJunitTest {

    @Test
    public void test() {
        System.out.println(MySingleton.INSTANCE.getI());
        MySingleton.INSTANCE.setI(1);
        System.out.println(MySingleton.INSTANCE.getI());
    }
}

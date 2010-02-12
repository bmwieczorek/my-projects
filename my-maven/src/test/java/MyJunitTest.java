import org.junit.Test;


public class MyJunitTest {

	@Test
	public void test(){
		System.out.println(MySingleton.INSTANCE.i);
		MySingleton.INSTANCE.i = 1;
		System.out.println(MySingleton.INSTANCE.i);
	}
}

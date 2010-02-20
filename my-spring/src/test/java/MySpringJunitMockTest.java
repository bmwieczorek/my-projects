import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/my-test-context-impl.xml" })
public class MySpringJunitMockTest {

	@Autowired
	private MyDataSource myDataSource;
	
	@Test
	public void test(){
		myDataSource.print();
	}
}
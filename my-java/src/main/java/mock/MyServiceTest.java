package mock;
import org.junit.Test;


public class MyServiceTest {

	@Test
	public void testGetItemsSortedByName() {
		MyService myService = new MyService();
		DataStore mockDataStore = MyMock.mock(DataStore.class);
		myService.setDataStore(mockDataStore);

		Item item = new Item("house", 1000);

		MyMock.verify(mockDataStore.getItem(1)).thenReturn(item);

		System.out.println(myService.getItemsSortedByName(1));
	}

}

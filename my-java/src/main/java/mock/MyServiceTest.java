package mock;
import org.junit.Test;

public class MyServiceTest {

	@Test
	public void testGetItemsSortedByName() {
		MyService myService = new MyService();
		DataStore mockDataStore = MyMock.mock(DataStore.class);
		myService.setDataStore(mockDataStore);

		Item item1 = new Item("house", 1);
		Item item2 = new Item("car", 2);

		MyMock.verify(mockDataStore.getItem(1)).thenReturn(item1);
		MyMock.verify(mockDataStore.getItem(2)).thenReturn(item2);

		System.out.println(myService.getItemsSortedByName(2, 1));
	}
}

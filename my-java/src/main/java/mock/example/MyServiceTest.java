package mock.example;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import mock.MyMock;

public class MyServiceTest {

    @Test
    public void testGetItemsSortedByName() {
        MyService myService = new MyService();
        DataStore mockDataStore = MyMock.mock(DataStore.class);
        myService.setDataStore(mockDataStore);

        Item item1 = new Item("house", 1);
        Item item2 = new Item("car", 2);

        MyMock.when(mockDataStore.getItem(1)).thenReturn(item1);
        MyMock.when(mockDataStore.getItem(2)).thenReturn(item2);

        MyMock.verify(mockDataStore).getItem(2);
        List<Item> itemsSortedByName = myService.getItemsSortedByName(2, 1);
        System.out.println(itemsSortedByName);
        assertEquals(2, itemsSortedByName.size());
    }
}

package mock.example;

import java.util.ArrayList;
import java.util.List;

public class MyService {

    private DataStore dataStore;

    public List<Item> getItemsSortedByName(int... ids) {
        List<Item> items = new ArrayList<Item>();
        for (int id : ids) {
            items.add(dataStore.getItem(id));
        }
        return items;
    }

    public void setDataStore(DataStore dataStore) {
        this.dataStore = dataStore;
    }

}

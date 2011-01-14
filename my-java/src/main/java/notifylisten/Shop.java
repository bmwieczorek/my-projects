package notifylisten;

import java.util.ArrayList;
import java.util.List;

class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

// interface to implement by concrete listener instances in order to be notified
// on sold item
interface Listener {
    void onSoldItem(Item item);
}

// abstract class on providing only default implementation of on sold item
// method
abstract class AbstractListener implements Listener {
    @Override
    public void onSoldItem(Item item) {
        System.out.println("[" + this.getClass().getName() + "] Received notification for " + "Item=" + item.getName()
                + ",price=" + item.getPrice());
    }
}

// actual listener implementations
class Accountant extends AbstractListener {
}

class HR extends AbstractListener {
}

// main app
public class Shop {
    private List<Listener> listeners = new ArrayList<Listener>();

    public static void main(String[] args) {
        Shop shop = new Shop();
        // shop explicitly register all listeners
        shop.addListener(new Accountant());
        shop.addListener(new HR());
        // sell items which will also notify listeners from the list
        shop.sell(new Item("CD", 777));
        shop.sell(new Item("Book", 123));
    }

    private void sell(Item item) {
        System.out.println("[" + this.getClass().getName() + "] Selling " + item.getName() + " for " + item.getPrice()
                + " ...");
        notifyListeners(item);
    }

    private void notifyListeners(Item item) {
        for (Listener listener : listeners) {
            listener.onSoldItem(item);
        }
    }

    private void addListener(Listener listener) {
        listeners.add(listener);
    }
}

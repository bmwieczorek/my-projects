package notifylisten;

import java.util.ArrayList;
import java.util.List;

class Item2 {
    private String name;
    private int price;

    public Item2(String name, int price) {
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

// interface to implement by concrete listener instances in order to be notified on sold item
interface Listener2 {
    void onSoldItem(Item2 item2);
}

// abstract class on providing default registration in the listeners list
abstract class AbstractListener2 implements Listener2 {
    public AbstractListener2(Shop2 shop2) {
        shop2.addListener(this);
    }

    public void onSoldItem(Item2 item2) {
        System.out.println("[" + this.getClass().getName() + "] Received notification for " + "Item="
                + item2.getName() + ",price=" + item2.getPrice());
    }
}

// actual listener implementations
class Accountant2 extends AbstractListener2 {
    public Accountant2(Shop2 shop2) {
        super(shop2);
    }
}

class HR2 extends AbstractListener2 {
    public HR2(Shop2 shop2) {
        super(shop2);
    }
}

// main app
public class Shop2 {
    private List<Listener2> listener2s = new ArrayList<Listener2>();

    public static void main(String[] args) {
        Shop2 shop2 = new Shop2();
        // create listeners which add themselves to listeners list
        new Accountant2(shop2);
        new HR2(shop2);
        // sell items which will also notify listeners from the list
        shop2.sell(new Item2("CD", 777));
        shop2.sell(new Item2("Book", 123));
    }

    private void sell(Item2 item2) {
        System.out.println("[" + this.getClass().getName() + "] Selling " + item2.getName() + " for "
                + item2.getPrice() + " ...");
        notifyListeners(item2);
    }

    private void notifyListeners(Item2 item2) {
        for (Listener2 listener2 : listener2s) {
            listener2.onSoldItem(item2);
        }
    }

    // used by listeners to register themselves to the listeners list
    public void addListener(Listener2 listener2) {
        listener2s.add(listener2);
    }
}

package designpatterns.flyweight;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Point {
    int price;

    String name;

    void advertise() {
        System.out.println(price + ":" + name);
    }

    public Point(int price, String name) {
        System.out.println("Created:" + price + ":" + name);
        this.price = price;
        this.name = name;
    }
}

class PointManager {
    List<Point> points = new ArrayList<Point>();

    void add(Point point) {
        points.add(point);
    }

    void advertise() {
        for (Point point : points) {
            point.advertise();
        }
    }
}

class PointFlyWeight {
    int counter = 0;
    int[] prices = new int[100];
    String[] names = new String[100];
    PriceFactory priceFactory = new PriceFactory();
    NameFactory nameFactory = new NameFactory();

    public PointFlyWeight() {
        System.out.println("created pointflyweight");
    }

    void create(int price, String name) {
        prices[counter] = price;
        names[counter] = name;
    }

    void advertise() {
        for (int i = 0; i < counter; i++) {
            System.out.println(prices[i] + ":" + names[i]);
        }
    }

    class PriceFactory {
        private int[] cache = new int[100];

        private int numberOfCachedEntries = 0;

        public int create(int price) {
            if (numberOfCachedEntries > 0) {
                for (int i = 0; i < numberOfCachedEntries; i++) {
                    if (price == cache[i])
                        return price;
                }
            }
            cache[numberOfCachedEntries++] = price;
            return price;
        }

        int getNumberOfCachedItems() {
            return numberOfCachedEntries;
        }
    }

    class NameFactory {
        private Set<String> cache = new HashSet<String>();

        public String create(String name) {
            if (!cache.contains(name))
                cache.add(name);
            return name;
        }

        int getNumberOfCachedItems() {
            return cache.size();
        }

    }
}

public class Example {

    public static void main(String[] args) {
        Point point = new Point(1, "a");
        Point point2 = new Point(2, "bb");
        Point point3 = new Point(1, "bb");
        point.advertise();
        point2.advertise();
        point3.advertise();
        //        
        // PriceFactory priceFactory = new PriceFactory();
        // NameFactory nameFactory = new NameFactory();
        // PointFlyWeight pointFlyWeight = new PointFlyWeight();
        // pointFlyWeight.create(price, name);

    }
}

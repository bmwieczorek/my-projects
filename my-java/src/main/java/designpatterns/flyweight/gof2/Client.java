package designpatterns.flyweight.gof2;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private static FlyweightFactory factory = new FlyweightFactory();

    public static void main(String[] args) {
        Column column = factory.createColumn(createRows("Hello", "World"));
        column.draw(new Context());

    }

    private static List<Row> createRows(String... rowText) {
        List<Row> rows = new ArrayList<Row>();
        for (String text : rowText) {
            rows.add(factory.createRow(text));
        }
        return rows;
    }

}

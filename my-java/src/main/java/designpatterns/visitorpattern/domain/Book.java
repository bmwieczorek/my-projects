package designpatterns.visitorpattern.domain;

import designpatterns.visitorpattern.visitor.Visitor;

public class Book implements Item {

    private final String name;
    private final int price;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getDescription() {
        return "Book: \"" + name + "\", price: " + price + "\"$";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

}

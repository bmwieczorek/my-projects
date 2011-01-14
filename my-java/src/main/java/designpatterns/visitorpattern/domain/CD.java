package designpatterns.visitorpattern.domain;

import designpatterns.visitorpattern.visitor.Visitor;

public class CD implements Item {
    private final String name;
    private final int price;

    public CD(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getDescription() {
        return "CD: \"" + name + "\", price: " + price + "\"$";
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

}

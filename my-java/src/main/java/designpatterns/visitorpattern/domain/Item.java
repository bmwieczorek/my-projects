package designpatterns.visitorpattern.domain;

import designpatterns.visitorpattern.visitor.Visitor;

public interface Item {
    int getPrice();

    String getDescription();

    void accept(Visitor visitor);
}

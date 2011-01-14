package designpatterns.visitorpattern.visitor;

import designpatterns.visitorpattern.domain.Book;
import designpatterns.visitorpattern.domain.CD;

public class PromotionVisitorImpl implements Visitor {

    @Override
    public void visit(Book book) {
        System.out.print("New book promotion: " + book.getDescription() + " ! Buy now!");
    }

    @Override
    public void visit(CD cd) {
        System.out.print("Super promotion new week: Only " + cd.getDescription() + " !");
    }

}

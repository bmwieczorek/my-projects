package designpatterns.visitorpattern.visitor;

import designpatterns.visitorpattern.domain.Book;
import designpatterns.visitorpattern.domain.CD;

public class UrlCreatorVisitor implements Visitor {

    @Override
    public void visit(Book book) {
        System.out.print("http://bawi.com/store/books/" + book.getName().replace(" ", "+"));
    }

    @Override
    public void visit(CD cd) {
        System.out.print("http://bawi.com/store/cds/" + cd.getName().replace(" ", "+"));
    }

}

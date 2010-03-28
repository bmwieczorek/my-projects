package designpatterns.visitorpattern.visitor;

import designpatterns.visitorpattern.domain.Book;
import designpatterns.visitorpattern.domain.CD;

public interface Visitor {
	void visit(Book book);
	void visit(CD cd);
}

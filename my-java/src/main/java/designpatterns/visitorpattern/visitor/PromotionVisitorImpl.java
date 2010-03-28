package designpatterns.visitorpattern.visitor;

import designpatterns.visitorpattern.domain.Book;
import designpatterns.visitorpattern.domain.CD;

public class PromotionVisitorImpl implements Visitor {

	public void visit(Book book) {
		System.out.print("New book promotion: " + book.getDescription()
				+ " ! Buy now!");
	}

	public void visit(CD cd) {
		System.out.print("Super promotion new week: Only "
				+ cd.getDescription() + " !");
	}

}

package designpatterns.visitorpattern;

import java.util.ArrayList;
import java.util.List;

import designpatterns.visitorpattern.domain.Book;
import designpatterns.visitorpattern.domain.CD;
import designpatterns.visitorpattern.domain.Item;
import designpatterns.visitorpattern.visitor.PromotionVisitorImpl;
import designpatterns.visitorpattern.visitor.UrlCreatorVisitor;
import designpatterns.visitorpattern.visitor.Visitor;



public class Shopping {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Item> items = new ArrayList<Item>(2);
		Item book = new Book("TIJ4", 100);
		Item cd = new CD("Fink Floyd", 50);
		items.add(book);
		items.add(cd);

		Visitor promotionVisitor = new PromotionVisitorImpl();
		Visitor urlCreatorVisitor = new UrlCreatorVisitor();
		for (Item item : items) {
			item.visit(promotionVisitor);
			System.out.print(" See: ");
			item.visit(urlCreatorVisitor);
			System.out.println("");
		}

	}

}

package algorithms;

class Element {
	int value;
	Element next;

	public Element(int value, Element next) {
		this.value = value;
		this.next = next;
	}
}

public class LinkedListRemoval {
	
	public static void main(String[] args) {
		// Element e = new Element(1, new Element(2, new Element(3, null)));
		
		Element element = generateLinkedList(10);
		printLinkedList(element);
		
	}

	private static Element generateLinkedList(int i) {
		Element last = new Element(i, null);
		for (--i; i > 0; i--) {
			last = new Element(i, last);
		}
		return last;
	}

	private static void printLinkedList(Element current) {
		while (current != null) {
			System.out.println(current.value);
			current = current.next;
		}
	}
	
	
}

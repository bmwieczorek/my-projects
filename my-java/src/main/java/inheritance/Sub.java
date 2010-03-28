package inheritance;

import java.util.Date;

public class Sub extends Super {
	private final Date date; 
	
	public Sub() {
		date = new Date();
	}

	@Override
	void myMethod() {
		System.out.println(date);
	}
	
	public static void main(String[] args) {
		Super sub = new Sub();
		sub.myMethod();
	}

}

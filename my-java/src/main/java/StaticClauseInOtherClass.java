class St {
	static {
		System.out.println("Static init!");
	}

	static void f() {
		// j++; //cannot make static ref to non static field
		new St().j++; // this is allowed
		i++;
	};

	private int j;

	static int i = 10;

	int ff() {
		i++;
		return i;
	}
}

class Outer {
	int i;
	static int j;

	void g() {
		new Inner().f();
	}

	// static class Inner {
	class Inner {
		int k;

		void f() {

			//
			k = Outer.j;

			k = new Outer().i;

			// provide ab access outer class object via OuterClassName.this
			k = Outer.this.i; // this will not work if Inner class is static
			// static inner class does not have a reference to the outer class
			// object, ie. cannot access non static fields and methods from the
			// outer class

		}
	}

	public static void main(String[] args) {
		// Outer.Inner inner = new Outer().new Inner(); //or
		Outer outer = new Outer();

		// must use and instance of outer class to create an instance of an
		// inner class
		// .new creates and instance of the inner class
		Inner inner = outer.new Inner(); // works for non-static Inner class,
											// not for static

		// Inner inner2 = new Inner(); //works for static Inner class, not for
		// non-static
		// no need an object of an outer class to create an inner static class
		inner.f();
	}
}

public class StaticClauseInOtherClass {
	public static void main(String[] args) {
		System.out.println("In main!");
		// no need to create and object instance
		// St.f(); //access static method
		// System.out.println(St.i); //access static field

		new St();
		//new St().i++;
	}
}

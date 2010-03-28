package initializationorder;

class A { public A() { System.out.println("new A()"); } }
class B { public B() { System.out.println("new B()"); } }

class Base{
	public Base() { System.out.println("new Base()"); }
	A a = new A();
	static { System.out.println("Static base"); }
}

public class Sub extends Base {
	public static void main(String[] args) {
		System.out.println("main()");
		new Sub();
	}
	public Sub() { System.out.println("new Sub()"); }
	B b = new B();
	static { System.out.println("Static sub"); }
}

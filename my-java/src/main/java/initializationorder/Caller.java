package initializationorder;

class Callee{
	public Callee() { System.out.println("new Callee()"); }
	static {
	    System.out.println("mama");
	}
}

class StaticClass{
	public StaticClass() { System.out.println("new StaticClass"); }
}

//variables are initialized before any method can be called, even before constructor
//first variable initialization, the main method execution

public class Caller {
	public Caller() { System.out.println("new Caller()"); }
	
	//second executed static main method
    //To execute main( ) the Caller class must be loaded, and its static fields are then initialized
	//which causes those classes to be loaded

	public static void main(String[] args) {
	    System.out.println("inside main");
	    //new Caller(); 
	}
	
	//initialization: after static fields/blocks then member variables
	//static Callee callee; // = new Callee();
	Callee callee = new Callee();
	
	//initialization: first static fields or/and static block in the definition order
	//There’s only a single piece of storage for a static, regardless of how many objects are created.
	//You can’t apply the static keyword to local variables, so it only applies to fields.
	static StaticClass staticClass = new StaticClass();;
	static { System.out.println("Static initialize"); }
}

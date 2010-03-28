package exceptions;

public class ExceptionTest {

	static void f(){
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {		
		f();
		System.err.print(1+11);
		
	}

}

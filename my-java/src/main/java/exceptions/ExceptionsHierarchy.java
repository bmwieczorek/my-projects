package exceptions;

public class ExceptionsHierarchy {

	public static void main(String[] args) {
		System.out.println(new RuntimeException() instanceof Exception);
		System.out.println(new IllegalArgumentException() instanceof Exception);
	}

}

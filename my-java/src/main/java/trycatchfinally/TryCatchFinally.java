package trycatchfinally;

public class TryCatchFinally {

	public static void main(String[] args) {
		System.out.println(myF());
	}

	private static String myF() {
		try {
			return "Try";
		} finally {
			System.out.println("Finally");
		}
	}

}

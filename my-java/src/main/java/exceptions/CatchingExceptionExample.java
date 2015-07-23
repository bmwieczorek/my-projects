package exceptions;

public class CatchingExceptionExample {

    public static void main(String[] args) {
        int i = 0;
        int result = 0;
        try {
            result = 10 / i;
        } catch (ArithmeticException ae) {
            System.out.println("In catch ArithmeticException: " + ae.getMessage() + ", result=" + result);
        } finally {
            System.out.println("In finally, result=" + result);
        }
        System.out.println("Out of try-catch-finally, result=" + result);
    }

}

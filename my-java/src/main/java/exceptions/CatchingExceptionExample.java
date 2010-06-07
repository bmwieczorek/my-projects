package exceptions;

public class CatchingExceptionExample {

    public static void main(String[] args) {

        int i = 0;
        int result = 0;
        try {
            result = 10 / i;

            // } catch (IllegalArgumentException ae) {
        } catch (ArithmeticException ae) {
            // ae.printStackTrace();
            System.out.println("Catching aryth exception: " + ae.getMessage());
        } finally {
            System.out.println("In finally, result=" + result);
        }
        System.out.println("Out of tcf, result=" + result);
    }

}

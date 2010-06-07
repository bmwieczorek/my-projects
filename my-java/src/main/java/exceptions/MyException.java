package exceptions;

public class MyException extends Exception {

    private static final long serialVersionUID = 1L;

    public MyException(String message) {
        super(message);
    }

    public MyException() {
        super();
    }

    public MyException(String message, Throwable t) {
        super(message, t);
    }
}

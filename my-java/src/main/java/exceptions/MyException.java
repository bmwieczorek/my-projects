package exceptions;

public class MyException extends Exception {
    
    public MyException(String message) {
        super(message);
    }
    
    public MyException(){
        super();
    }
    public MyException(String message, Throwable t){
        super(message, t);
    }
}

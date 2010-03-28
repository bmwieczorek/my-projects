package exceptions;

public class ChildClassWithMethodThrowingException {
    
    public void myMethod() throws MyException {
        throw new MyException("Child-my-ex");
    }

}

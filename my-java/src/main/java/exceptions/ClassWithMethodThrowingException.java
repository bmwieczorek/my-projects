package exceptions;

public class ClassWithMethodThrowingException {

    public void myMethod() throws MyException {
        new ChildClassWithMethodThrowingException().myMethod();
    }

}

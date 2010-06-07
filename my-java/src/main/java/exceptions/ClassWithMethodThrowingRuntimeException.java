package exceptions;

public class ClassWithMethodThrowingRuntimeException {

    /**
     * @throws MyRuntimeException
     */
    public void myMethod() {
        throw new MyRuntimeException("my RE");
    }

}

package exceptions;

public class Example {

    public static void main(String[] args) throws MyException {
        try {
            new ClassWithMethodThrowingException().myMethod();
        } catch (MyException e) {
            e.printStackTrace();
            System.out.println(e.getCause());
            throw new MyException("My handling", e);
        }

        // new ClassWithMethodThrowingRuntimeException().myMethod();
    }

}

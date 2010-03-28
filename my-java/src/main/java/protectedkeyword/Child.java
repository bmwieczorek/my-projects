package protectedkeyword;

public class Child extends Parent{

    protected void checkParam(int i) {
        System.err.println("i=" + i);
    }
    
}

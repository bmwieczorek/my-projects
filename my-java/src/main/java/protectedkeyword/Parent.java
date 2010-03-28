package protectedkeyword;

public class Parent {

    int i = 0;

    public void setI(int i) {
        checkParam(i);
        this.i = i;
    }

    //checkParam is static - it is automatically final,
    
    protected void checkParam(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("i cannot be negative (i=" + i
                    + ")");
        }
    }

}

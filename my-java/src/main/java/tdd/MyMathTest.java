package tdd;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyMathTest {

    @Test
    public final void testAdd() {
        int a = 1;
        int b = 2;
        MyMath math = new MyMath();
        assertEquals((a + b), math.add(a, b));
    }

    @Test
    public final void testDivide() {
        int a = 1;
        int b = 0;
        MyMath math = new MyMath();
        try {
            math.divide(a, b);
            fail("Should try arithm exc");
        } catch (Exception e) {
        }
    }

}

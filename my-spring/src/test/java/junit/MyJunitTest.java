package junit;

import org.junit.Test;

public class MyJunitTest {

    String text = staticSay("field");
    static String staticText = staticSay("static field");

    public MyJunitTest() {
        System.out.println("constructor");
    }

    public MyJunitTest(String text) {
        System.out.println("constructor: " + text);
    }

    @Test
    public void myTestCase1() {
    }

    @Test
    public void myTestCase2() {
    }

    String say(String text) {
        return staticSay(text);
    }

    static String staticSay(String text) {
        System.out.println(text);
        return text;
    }
}

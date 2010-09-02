class BaseClass {

    static String myStaticText = basesay("base static");

    String myText = basesay("base local field");

    public BaseClass() {
        basesay("base constructor");
    }

    static String basesay(String text) {
        System.out.println(text);
        return text;
    }
}

public class InitializationTest extends BaseClass {

    static String myStaticText = say("static");

    String myText = say("local field");

    public InitializationTest() {
        say("constructor");
    }

    public static void main(String[] args) {
        new InitializationTest();
    }

    static String say(String text) {
        System.out.println(text);
        return text;
    }
}

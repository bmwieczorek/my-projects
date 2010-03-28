public class C1 {

    private static StringBuffer buffer = new StringBuffer();
    static {
        buffer.append("Hello World");
    }

    public C1() {
    }

    public static String getString() {
        return buffer.toString();
    }

}
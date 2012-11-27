package overloading;

public class MethodOverloading {
    public static void a(Object o) {
        System.out.println("o");
    }

    public static void a(String s) {
        System.out.println("s");
    }

    // public static void a(Number n) {
    // System.out.println("n");
    // }

    public static void main(String[] args) {
        a(null);
        a("a");

        // Object[] objs = { null, "a" };
        // for (Object object : objs) {
        // a(object);
        // }
    }

}

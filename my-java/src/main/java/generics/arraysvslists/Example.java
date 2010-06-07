package generics.arraysvslists;

public class Example {
    public static void main(String[] args) {
        Object[] os = new Integer[1];
        os[0] = "Hello";
        System.out.println(os[0]);

    }

    @SuppressWarnings("unchecked")
    static <T> T[] f(T[] ts) {
        // new T[1]; // compilation error
        @SuppressWarnings("unused")
        T[] objs = (T[]) new Object[100];
        return ts;
    }

}

package autoboxing;

public class NPEExample {
    public static void main(String[] args) {
        int i = returnInteger();
        System.out.println(i);
    }

    private static Integer returnInteger() {
        return null;
    }
}

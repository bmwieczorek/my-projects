class SuperMyClass {
    final String a = "aaaa";

    final void print() {

    }
}

public class MyClass extends SuperMyClass {
    // if SuperMyClass is not final than subclass overrides 'a' field
    String a = "bbb";

    public static void main(String[] args) {
        MyClass myc = new MyClass();
        myc.a = "ccc";
    }

}

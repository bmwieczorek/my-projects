class A {
    public void a() {
    }
}

class B extends A {

}

public class Instance {
    public static void main(String[] args) {
        System.out.println(null instanceof A);
    }

    static A create() {
        return new B();
    }
}

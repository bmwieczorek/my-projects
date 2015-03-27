package mypckg;

class A {
}

class B extends A {
}

class C extends A implements Runnable {
    @Override
    public void run() {
    }
}

class D extends C {
}

class E extends C {
}

interface S {
}

interface W {
}

abstract class T {
}

class V extends T implements S, W {
}

@SuppressWarnings("unused") 
public class Example {
    static void m(T w) {

    }

    @SuppressWarnings({ "cast" })
    public static void main(String[] args) {

        E e = new E();
        // D d = (D) e; // compilation failure
        C c = new C();
        A a = (c);
        a.equals(null);

        V v = new V();
        m(v);
        S s = v;
    }

}

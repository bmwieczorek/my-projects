public class InitializationOrder {

    // properties are initialized before any methods, even constructors

    class A {
        int i = 0;

        public A() {
            i = 0;
            System.out.print("In A const");
        }

        void reset() {
            i = -1;
        }

    }

    class B {
        A a = new A();

        public B() {
            System.out.print("In B const");
            a.reset();
        }
    }

    public static void main(String[] args) {
        InitializationOrder init = new InitializationOrder();
        B b = init.new B();
        System.out.print(b.a.i);

    }

}

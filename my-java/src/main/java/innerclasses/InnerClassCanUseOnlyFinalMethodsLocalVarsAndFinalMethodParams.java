package innerclasses;

class A {
    private int i;

    int value() {
        return i;
    }

    A(int i) {
        this.i = i;
    }
}

abstract class B {
    B(String s) {
        System.out.println("s=" + s);
    }
    abstract void myf();
}

public class InnerClassCanUseOnlyFinalMethodsLocalVarsAndFinalMethodParams {
    int memberVariable1 = 1;

    A myMethod(final int parameter) {
        final int localVariable = 3;

        // return instance of anonymous class. As it is an inner class it can
        // use:
        // - final and non-final memberVariables
        // - only final parameters of enclosing method
        // - only final local variables of enclosing method
        return new A(memberVariable1) { // A(int) base class constructor
            @Override
            int value() {
                return memberVariable1 * super.value() * parameter
                        * localVariable;
            }
        };
    }

    String s = "Ala ma kota";

    B b() {
        return new B(s) {
            @Override
            void myf() {
            }
        };
    }

    public static void main(String[] args) {
        InnerClassCanUseOnlyFinalMethodsLocalVarsAndFinalMethodParams ac2 = new InnerClassCanUseOnlyFinalMethodsLocalVarsAndFinalMethodParams();
        System.out.println(ac2.myMethod(3).value());
        ac2.b();
    }
}

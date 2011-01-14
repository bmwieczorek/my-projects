package innerclasses;

public class InnerStaticClassAccessOnlyStaticOuterFieldsMethodsVsNonstaticBoth {

    void outerNop() {
    }

    static void outerStaticNop() {
    }

    // non static inner class has access to both static and instance outer
    // variables/methods
    public class InnerClass {
        void innerNop() {
            // access to non static
            outerNop();
            InnerStaticClassAccessOnlyStaticOuterFieldsMethodsVsNonstaticBoth.this.outerNop(); // alternative
            // access to static as well
            outerStaticNop();
            InnerStaticClassAccessOnlyStaticOuterFieldsMethodsVsNonstaticBoth.outerStaticNop(); // alternative
        }
    }

    // static inner class has access only to static outer methods/fields
    public static class InnerStatic {
        void innerStaticNop() {
            // only access to static
            outerStaticNop();
            InnerStaticClassAccessOnlyStaticOuterFieldsMethodsVsNonstaticBoth.outerStaticNop(); // alternative

            // attention - static inner class can access non static
            // methods/fields via an instance of the outer class
            new InnerStaticClassAccessOnlyStaticOuterFieldsMethodsVsNonstaticBoth().outerNop();
        }
    }
}

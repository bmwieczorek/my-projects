class Abcde {
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Abcde) ? true : false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

public class Equality {
    public static void main(String[] args) {

        int i = 1;
        int ii = 1;
        System.out.println(i == ii); // always true (for primitives)

        // with assignment '= ""' comparison will be true
        String s = "string";
        String ss = "string";
        System.out.println(s == ss); // true
        System.out.println(s.equals(ss)); // true

        // 1. for objects == compares if two reference point to the same memory
        // location
        // 2. default not overridden equals uses '==' comparison

        // different approach
        String s2 = new String("A");
        String ss2 = new String("A");
        System.out.println(s2 == ss2); // false
        System.out.println(s2.equals(ss2)); // true

        Abcde a = new Abcde();
        Abcde aa = new Abcde();
        System.out.println(a == aa); // always false
        System.out.println(a.equals(aa)); // false if A not overrides equals,
        // then == is used (memory location); true if (return (obj instanceof A)
        // ? true : false;)
    }

}

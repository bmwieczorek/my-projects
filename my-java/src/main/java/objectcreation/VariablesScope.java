package objectcreation;

class Abcd {
    String color;

    @Override
    public String toString() {
        return color;
    }
}

public class VariablesScope {

    // can have a value not assigned, but still can be used
    // system assigns the default values 0/null/false
    // objects are created on the heap, instance variables (as well)
    // heap is shared amongst threads
    int heapMemberVariable;

    int mv = heapMemberVariable;

    private static void myMethod() {
        // local variable has to be initialized in order to be used
        // stack (LIFO) - separate for each thread, each thread executing a
        // method has its own copy of local variables;
        // -Xss to increase the stack
        int stackLocalVariable = 0;
        int lv = stackLocalVariable;
        System.out.println(lv);
    }

    public static void main(String[] args) {
        myMethod();
        Abcd a = new Abcd();
        a.color = "red";
        Abcd b = a;
        a = null;
        System.out.println(b);
        System.out.println(a);

    }

    // idempotent method - repeated calls with the same args give the same
    // results
}

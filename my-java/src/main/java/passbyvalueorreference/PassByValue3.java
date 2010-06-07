package passbyvalueorreference;

class AAA {
    public int x;

    public AAA(int x) {
        this.x = x;
    }

    public String toString() {
        return Integer.toString(x);
    }
}

public class PassByValue3 {
    static void f(AAA arg1) {
        arg1.x = 10;
    }

    public static void main(String args[]) {
        AAA arg1 = new AAA(5);

        f(arg1);

        System.out.println("arg1 = " + arg1);
    }
}

package passbyvalueorreference;

class AA {
    public int x;

    AA(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return Integer.toString(x);
    }
}
@SuppressWarnings("unused") 
public class PassByValue2 {
    static void f(AA arg1) {
        arg1 = null;
    }

    public static void main(String args[]) {
        AA arg1 = new AA(5);

        f(arg1);

        System.out.println("arg1 = " + arg1);
    }
}

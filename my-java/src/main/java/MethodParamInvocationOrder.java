public class MethodParamInvocationOrder {

    int square(int a) {
        System.out.println("Square " + a);
        return a * a;
    }

    void print(int i) {
        System.out.println(i);
    }

    public static void main(String[] args) {
        MethodParamInvocationOrder m = new MethodParamInvocationOrder();
        m.print(m.square(5));
    }
}

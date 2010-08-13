public class MethodParamInvocationOrder {
    int i;

    MethodParamInvocationOrder() {
        System.out.println("constr");
    }

    static int square(int a) {
        System.out.println("Square " + a);
        return a * a;
    }

    void print(int i) {
        System.out.println(i);
    }

    public static void main(String[] args) {
        // MethodParamInvocationOrder m = new MethodParamInvocationOrder();
        // m.print(m.square(5));

        int square = MethodParamInvocationOrder.square(10);
        new MethodParamInvocationOrder().withNumber(square);
    }

    private MethodParamInvocationOrder withNumber(int i) {
        this.i = i;
        System.out.println("with number");
        return this;
    }
}

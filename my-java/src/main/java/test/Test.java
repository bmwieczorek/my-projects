package test;


public class Test implements java.io.Serializable {
    public static void main(String[] args) {
        System.out.println(func(4));
        System.out.println(4f);

    }

    static public double SquareRoot(double value) throws ArithmeticException {
        if (value >= 0)
            return Math.sqrt(value);
        else
            throw new ArithmeticException();
    }

    static public double func(int x) {
        double y = (double) x;
        try {
            y = SquareRoot(y);
        } catch (ArithmeticException e) {
            y = 0;
        } finally {
            --y;
        }
        return y;
    }
}
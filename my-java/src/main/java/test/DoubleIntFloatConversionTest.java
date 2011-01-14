package test;

public class DoubleIntFloatConversionTest {
    public static void main(String[] args) {
        System.out.println(func(4));
        System.out.println(4f);

    }

    static public double SquareRoot(double value) throws ArithmeticException {
        if (value >= 0)
            return Math.sqrt(value);
        throw new ArithmeticException();
    }

    static public double func(int x) {
        double y = x;
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